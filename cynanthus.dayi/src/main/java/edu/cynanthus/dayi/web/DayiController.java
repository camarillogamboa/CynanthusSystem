package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class DayiController extends CommonController {

    @Autowired
    public DayiController(AuriSession auriSession) {
        super(auriSession);
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("emptyServer", new ServerInfo());
        return "index";
    }

    @GetMapping("/servers")
    public String serverList(Model model) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));
        return "components/navegation::serverNavList";
    }

    @GetMapping("/server/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);
        model.addAttribute("currentServer", serverInfo);
        return "components/server::currentServerView";
    }

    @PostMapping("/server")
    public ResponseEntity<?> saveServer(ServerInfo serverInfo) {
        if (serverInfo.getId() == null) serverInfo = auriSession.serverInfoService().create(serverInfo);
        else serverInfo = auriSession.serverInfoService().update(serverInfo);

        System.out.println("Se guardó: " + serverInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sets")
    public String setsView(Model model) {
        List<? extends InstructionSet> sets = auriSession.instructionSetService().readOnlySets();

        model.addAttribute("sets", sets);
        model.addAttribute("emptySet", new InstructionSet());

        return "components/instructions::instructionsView";
    }

    @PostMapping("/sets")
    public ResponseEntity<?> saveSet(InstructionSet instructionSet) {
        if (instructionSet.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se guardó: " + instructionSet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sets/{id:\\d+}")
    public ResponseEntity<?> saveInstruction(
        InstructionSet instructionSet,
        @RequestBody Instruction instruction
    ) {
        instructionSet.setInstructions(List.of(instruction));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sets/{id:\\d+}")
    public String setView(InstructionSet instructionSet, Model model) {
        instructionSet = auriSession.instructionSetService().read(instructionSet);

        model.addAttribute("currentSet", instructionSet);
        model.addAttribute("emptyInstruction",new Instruction());

        return "components/instructions::currentSetView";
    }

    @DeleteMapping("/sets/{id:\\d+}/delete")
    public ResponseEntity<?> deleteSet(InstructionSet instructionSet) {
        instructionSet = auriSession.instructionSetService().delete(instructionSet);
        System.out.println("Registro eliminado: " + instructionSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public String usersView(Model model) {
        return "components/users::usersView";
    }

}
