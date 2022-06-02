package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.dayi.domain.Theme;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("emptyServer", new ServerInfo());
        setTheme(model);
        return "index";
    }

    @GetMapping("/summary")
    public String summaryView(Model model) {
        setTheme(model);
        return "components/summary::dayiSummary";
    }

    @GetMapping("/servers/list")
    public String serverListView(Model model) {
        loadServers(model);
        setTheme(model);
        return "components/navegation::serverNavList";
    }

    private void loadServers(Model model) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));
    }

    private void setTheme(Model model) {
        model.addAttribute("theme", Theme.LIGHT_THEME);
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-SERVER->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping("/servers/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);
        model.addAttribute("currentServer", serverInfo);
        setTheme(model);
        return "components/server::currentServerView";
    }

    @GetMapping("/servers/{id:\\d+}/summary")
    public String serverSummaryView(ServerInfo serverInfo, Model model) {
        setTheme(model);
        return "components/server::serverSummaryView";
    }

    @PostMapping("/servers")
    public ResponseEntity<?> saveServer(@RequestBody ServerInfo serverInfo) {
        if (serverInfo.getId() == null) serverInfo = auriSession.serverInfoService().create(serverInfo);
        else serverInfo = auriSession.serverInfoService().update(serverInfo);

        System.out.println("Se guardó: " + serverInfo);

        return new ResponseEntity<>(serverInfo, HttpStatus.OK);
    }

    @DeleteMapping("/servers/{id:\\d+}")
    public ResponseEntity<?> deleteServer(ServerInfo serverInfo) {
        serverInfo = auriSession.serverInfoService().delete(serverInfo);

        System.out.println("Se eliminó " + serverInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sensing")
    public String sensingView(Model model) {
        setTheme(model);
        return "components/server::sensingView";
    }

    @GetMapping("/control")
    public String controlView(Model model) {
        setTheme(model);
        return "components/server::controlView";
    }

    /*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-INSTRUCTIONS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/

    @GetMapping("/sets")
    public String setsView(Model model) {
        loadSetList(model);
        model.addAttribute("emptySet", new InstructionSet());
        setTheme(model);

        return "components/instructions::instructionsView";
    }

    @GetMapping("/sets/summary")
    public String setsSummaryView(Model model) {
        setTheme(model);
        return "components/instructions::instructionsSummaryView";
    }

    @GetMapping("/sets/list")
    public String setListView(Model model) {
        loadSetList(model);
        return "components/instructions::setsBar";
    }

    @PostMapping("/sets")
    public ResponseEntity<?> saveSet(@RequestBody InstructionSet instructionSet) {
        System.out.println(instructionSet);
        if (instructionSet.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se guardó: " + instructionSet);

        return new ResponseEntity<>(instructionSet, HttpStatus.OK);
    }

    @GetMapping("/sets/{id:\\d+}")
    public String setView(InstructionSet instructionSet, Model model) {
        instructionSet = auriSession.instructionSetService().read(instructionSet);

        model.addAttribute("currentSet", instructionSet);
        model.addAttribute("emptyInstruction", new Instruction());
        setTheme(model);

        return "components/instructions::currentSetView";
    }

    @DeleteMapping("/sets/{id:\\d+}")
    public ResponseEntity<?> deleteSet(InstructionSet instructionSet) {
        instructionSet = auriSession.instructionSetService().delete(instructionSet);
        System.out.println("Registro eliminado: " + instructionSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sets/{id:\\d+}")
    public ResponseEntity<?> saveInstruction(InstructionSet instructionSet, @RequestBody Instruction instruction) {
        System.out.println(instructionSet);
        System.out.println(instruction);
        instructionSet.setInstructions(List.of(instruction));

        if (instruction.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se creó " + instructionSet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sets/instruction/{id:\\d+}")
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

    @GetMapping("/users")
    public String usersView(Model model) {
        setTheme(model);
        return "components/users::usersView";
    }

}
