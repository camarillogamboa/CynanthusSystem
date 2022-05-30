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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DayiController extends CommonController {

    @Autowired
    public DayiController(AuriSession auriSession) {
        super(auriSession);
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @GetMapping
    public String home(Model model) {
        model.addAttribute("emptyServer", new ServerInfo());
        return "index";
    }

    @GetMapping("/welcome")
    public String welcomeView() {
        return "components/welcome::welcomeView";
    }

    @GetMapping("/servers")
    public String serverList(Model model) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));
        return "components/navegation::serverNavList";
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @GetMapping("/server/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);
        model.addAttribute("currentServer", serverInfo);
        return "components/server::currentServerView";
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

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @GetMapping("/sets")
    public String setsView(Model model) {
        List<? extends InstructionSet> sets = auriSession.instructionSetService().readOnlySets();

        model.addAttribute("sets", sets);
        model.addAttribute("emptySet", new InstructionSet());

        return "components/instructions::instructionsView";
    }

    @PostMapping("/sets")
    public ResponseEntity<?> saveSet(@RequestBody InstructionSet instructionSet) {
        System.out.println(instructionSet);
        if (instructionSet.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se guardó: " + instructionSet);

        return new ResponseEntity<>(instructionSet.getId(), HttpStatus.OK);
    }

    @GetMapping("/sets/{id:\\d+}")
    public String setView(InstructionSet instructionSet, Model model) {
        instructionSet = auriSession.instructionSetService().read(instructionSet);

        model.addAttribute("currentSet", instructionSet);
        model.addAttribute("emptyInstruction", new Instruction());

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

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @GetMapping("/users")
    public String usersView(Model model) {
        return "components/users::usersView";
    }

}
