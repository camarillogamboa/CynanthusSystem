package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.Config;
import edu.cynanthus.dayi.domain.PropertyInfo;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.dayi.util.PropertyInfoBuilder;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import edu.cynanthus.domain.config.LatiroConfig;
import edu.cynanthus.domain.config.SordidusConfig;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DayiController extends CommonController {

    @Autowired
    public DayiController(AuriSession auriSession) {
        super(auriSession);
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-HOME->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping
    public String homeView(Model model) {
        loadServers(model);

        ServerInfo defaulServer = new ServerInfo(
            null,
            "127.0.0.1",
            9005,
            ServerType.STORAGE,
            null
        );

        model.addAttribute("defaultServer", defaulServer);
        return "index";
    }

    @GetMapping("/summary")
    public String summaryView(Model model) {
        return "components/summary::dayiSummary";
    }

    @GetMapping("/server/list")
    public String serverListView(Model model) {
        loadServers(model);
        return "components/navegation::serverNavList";
    }

    private void loadServers(Model model) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-SERVER->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping("/server/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);
        model.addAttribute("currentServer", serverInfo);
        return "components/server::currentServerView";
    }

    @GetMapping("/server/{id:\\d+}/summary")
    public String serverSummaryView(ServerInfo serverInfo, Model model) {
        return "components/server::serverSummaryView";
    }

    @PostMapping("/server")
    public ResponseEntity<?> saveServer(@RequestBody ServerInfo serverInfo) {
        if (serverInfo.getId() == null) serverInfo = auriSession.serverInfoService().create(serverInfo);
        else serverInfo = auriSession.serverInfoService().update(serverInfo);

        System.out.println("Se guardó: " + serverInfo);

        return new ResponseEntity<>(serverInfo, HttpStatus.OK);
    }

    @DeleteMapping("/server/{id:\\d+}")
    public ResponseEntity<?> deleteServer(ServerInfo serverInfo) {
        serverInfo = auriSession.serverInfoService().delete(serverInfo);

        System.out.println("Se eliminó " + serverInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sordidus/{id:\\d+}")
    public ResponseEntity<?> updateSordidusProperties(ServerInfo serverInfo, @RequestBody SordidusConfig sordidusConfig) {
        Boolean done = auriSession.sordidusServerService().updateConfigOf(serverInfo, sordidusConfig);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/latiro/{id:\\d+}")
    public ResponseEntity<?> updateLatiroProperties(ServerInfo serverInfo, @RequestBody LatiroConfig sordidusConfig) {
        Boolean done = auriSession.latiroServerService().updateConfigOf(serverInfo, sordidusConfig);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/stris/{id:\\d+}")
    public ResponseEntity<?> updateStrisProperties(ServerInfo serverInfo, @RequestBody StrisConfig strisConfig) {
        Boolean done = auriSession.strisServerService().updateConfigOf(serverInfo, strisConfig);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sensing")
    public String sensingView(Model model) {
        return "components/server::sensingView";
    }

    @GetMapping("/control")
    public String controlView(Model model) {
        return "components/server::controlView";
    }

    @GetMapping("/server/{id:\\d+}/properties")
    public String serverPropertiesOf(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);

        Config config = auriSession.cynanthusServerService(serverInfo.getServerType()).getConfigOf(serverInfo);

        List<PropertyInfo> propertyInfos = new LinkedList<>();

        PropertyInfoBuilder.toList(config, propertyInfos);

        model.addAttribute("config", config);
        model.addAttribute("propertyInfos", propertyInfos);

        switch (serverInfo.getServerType()) {
            case STORAGE:
                return "components/server::sordidusPropertiesView";
            case STREAM_DATA:
                return "components/server::latiroPropertiesView";
            case CONTROL:
                return "components/server::strisPropertiesView";
        }

        throw new IllegalStateException();
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-INSTRUCTIONS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping("/set")
    public String setsView(Model model) {
        loadSetList(model);
        model.addAttribute("emptySet", new InstructionSet());

        return "components/instructions::instructionsView";
    }

    @GetMapping("/set/summary")
    public String setsSummaryView(Model model) {
        return "components/instructions::instructionsSummaryView";
    }

    @GetMapping("/set/list")
    public String setListView(Model model) {
        loadSetList(model);
        return "components/instructions::setsBar";
    }

    @PostMapping("/set")
    public ResponseEntity<?> saveSet(@RequestBody InstructionSet instructionSet) {
        System.out.println(instructionSet);
        if (instructionSet.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se guardó: " + instructionSet);

        return new ResponseEntity<>(instructionSet, HttpStatus.OK);
    }

    @GetMapping("/set/{id:\\d+}")
    public String setView(InstructionSet instructionSet, Model model) {
        instructionSet = auriSession.instructionSetService().read(instructionSet);

        model.addAttribute("currentSet", instructionSet);
        model.addAttribute("emptyInstruction", new Instruction());

        return "components/instructions::currentSetView";
    }

    @DeleteMapping("/set/{id:\\d+}")
    public ResponseEntity<?> deleteSet(InstructionSet instructionSet) {
        instructionSet = auriSession.instructionSetService().delete(instructionSet);
        System.out.println("Registro eliminado: " + instructionSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set/{id:\\d+}")
    public ResponseEntity<?> saveInstruction(InstructionSet instructionSet, @RequestBody Instruction instruction) {
        System.out.println(instructionSet);
        System.out.println(instruction);
        instructionSet.setInstructions(List.of(instruction));

        if (instruction.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se creó " + instructionSet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/set/instruction/{id:\\d+}")
    public ResponseEntity<?> deleteInstruction(@PathVariable Integer id) {
        Instruction instruction = auriSession.instructionSetService().deleteInstruction(id);
        System.out.println("Se eliminó " + instruction);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void loadSetList(Model model) {
        List<? extends InstructionSet> sets = auriSession.instructionSetService().readOnlySets();
        model.addAttribute("sets", sets);
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-USERS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping("/user")
    public String usersView(Model model) {
        return "components/users::usersView";
    }

}
