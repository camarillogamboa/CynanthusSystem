package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


public class DayiControllerBack extends CommonController {

    public DayiControllerBack(AuriSession auriSession) {
        super(auriSession);
    }

    /*-------------------------------------------------Vista principal------------------------------------------------*/
    @GetMapping
    public String home(Model model) {
        setViewOption(model, "WELCOME_VIEW");
        return internalHome(model);
    }

    private String internalHome(Model model) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));
        model.addAttribute("emptyServer", new ServerInfo());

        return "index";
    }

    private void setViewOption(Model model, String viewOption) {
        model.addAttribute("viewOption", viewOption);
    }

    /*-------------------------------------------------Vista Servidor-------------------------------------------------*/
    @GetMapping("/server/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model) {
        serverInfo = findServerInfo(serverInfo);
        String defaultOption = serverInfo.getServerType() == ServerType.STORAGE ? "SERVER_PROPERTIES" : "SERVER_NODES";
        return internalServerView(serverInfo, defaultOption, model);
    }

    @GetMapping("/server/{id:\\d+}/{serverOption:nodes|properties|log}")
    public String serverView(ServerInfo serverInfo, @PathVariable String serverOption, Model model) {
        serverInfo = findServerInfo(serverInfo);

        String cannonicalOption = "SERVER_" + serverOption.toUpperCase();

        return internalServerView(serverInfo, cannonicalOption, model);
    }

    @GetMapping("/server/{id:\\d+}/delete")
    public String deleteServer(ServerInfo serverInfo) {
        serverInfo = auriSession.serverInfoService().delete(serverInfo);
        System.out.println("Registro eliminado: " + serverInfo);

        return "redirect:/";
    }

    private String internalServerView(ServerInfo serverInfo, String serverOption, Model model) {

        setViewOption(model, "SERVER_VIEW");

        model.addAttribute("currentServer", serverInfo);
        model.addAttribute("serverOption", serverOption);

        return internalHome(model);
    }

    @PostMapping("/server")
    public String saveServer(ServerInfo serverInfo) {

        if (serverInfo.getId() == null) serverInfo = auriSession.serverInfoService().create(serverInfo);
        else serverInfo = auriSession.serverInfoService().update(serverInfo);

        System.out.println("Se guardó: " + serverInfo);

        return "redirect:/server/" + serverInfo.getId();
    }

    /*----------------------------------------------Vista de instrucciones--------------------------------------------*/

    @GetMapping("/sets")
    public String instructionsView(Model model) {
        setViewOption(model, "INSTRUCTIONS_VIEW");

        List<? extends InstructionSet> sets = auriSession.instructionSetService().readOnlySets();

        model.addAttribute("sets", sets);
        model.addAttribute("emptySet", new InstructionSet());

        return internalHome(model);
    }

    @GetMapping("/sets/{id:\\d+}")
    public String instructionSetView(InstructionSet instructionSet, Model model) {
        instructionSet = auriSession.instructionSetService().read(instructionSet);

        model.addAttribute("currentSet", instructionSet);

        return "components/instructions::currentSetView";
    }

    @PostMapping("/sets")
    public String saveInstructionSet(InstructionSet instructionSet) {
        if (instructionSet.getId() == null) instructionSet = auriSession.instructionSetService().create(instructionSet);
        else instructionSet = auriSession.instructionSetService().update(instructionSet);

        System.out.println("Se guardó: " + instructionSet);

        return "redirect:/sets/" + instructionSet.getId();
    }

    /*-----------------------------------------------Vista de usuarios------------------------------------------------*/

    @GetMapping("/users")
    public String usersView(Model model) {
        setViewOption(model, "USERS_VIEW");
        return internalHome(model);
    }

}
