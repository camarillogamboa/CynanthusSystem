package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.api.exception.ResourceNotFoundException;
import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.Config;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.dayi.util.PropertyInfo;
import edu.cynanthus.dayi.util.PropertyInfoBuilder;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cynanthus/dayi")
public class DayiController {

    private final AuriSession auriSession;

    @Autowired
    public DayiController(AuriSession auriSession) {
        this.auriSession = auriSession;
    }

    @GetMapping
    public String home(Model model, HttpServletRequest request) {
        setViewOption(model, "WELCOME_VIEW");
        return internalHome(model, request);
    }

    private String internalHome(Model model, HttpServletRequest request) {
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("serverSections", BeanUtil.toServerSections(serverInfos));

        Object currentPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        model.addAttribute("currentPath", currentPath);
        model.addAttribute("emptyServer", new ServerInfo());

        return "index";
    }

    @GetMapping("/server/{id:\\d+}")
    public String serverView(
        ServerInfo serverInfo,
        Model model,
        HttpServletRequest request
    ) {
        serverInfo = auriSession.serverInfoService().read(serverInfo);

        String defaultOption = serverInfo.getServerType() == ServerType.STORAGE ? "PROPERTIES" : "NODES";
        return internalServerView(serverInfo, defaultOption, model, request);
    }

    @GetMapping("/server/{id:\\d+}/nodes")
    public String serverNodesOption(ServerInfo serverInfo, Model model, HttpServletRequest request) {
        serverInfo = auriSession.serverInfoService().read(serverInfo);

        List<? extends GeneralNode<?>> nodes;
        switch (serverInfo.getServerType()) {
            case STREAM_DATA:
                nodes = auriSession.latiroServerService().getGeneralNodesOf(serverInfo, "*");
                break;
            case CONTROL:
                nodes = auriSession.strisServerService().getGeneralNodesOf(serverInfo, "*");
                break;
            default:
                throw new ResourceNotFoundException();
        }

        nodes.forEach(System.out::println);

        model.addAttribute("nodes", nodes);

        return internalServerView(serverInfo, "NODES", model, request);
    }

    @GetMapping("/server/{id:\\d+}/properties")
    public String serverPropertiesOption(ServerInfo serverInfo, Model model, HttpServletRequest request) {
        serverInfo = auriSession.serverInfoService().read(serverInfo);
        return internalServerView(serverInfo, "PROPERTIES", model, request);
    }

    @GetMapping("/server/{id:\\d+}/delete")
    public String deleteServer(ServerInfo serverInfo) {
        serverInfo = auriSession.serverInfoService().delete(serverInfo);
        System.out.println("Registro eliminado: " + serverInfo);

        return "redirect:/cynanthus/dayi";
    }

    private String internalServerView(
        ServerInfo serverInfo,
        String serverOption,
        Model model,
        HttpServletRequest request
    ) {
        Boolean available = auriSession.cynanthusServerService(serverInfo.getServerType()).isAvailable(serverInfo);

        setViewOption(model, "SERVER_VIEW");

        model.addAttribute("currentServer", serverInfo);
        model.addAttribute("availableServer", available);
        model.addAttribute("serverOption", serverOption);

        if (available) {
            Config config = auriSession.cynanthusServerService(serverInfo.getServerType()).getConfigOf(serverInfo);
            List<PropertyInfo> list = PropertyInfoBuilder.toPropertyInfoList(config);
            model.addAttribute("propertyList", list);
        }

        return internalHome(model, request);
    }

    @PostMapping("/server")
    public String saveServer(ServerInfo serverInfo) {

        if (serverInfo.getId() == null) serverInfo = auriSession.serverInfoService().create(serverInfo);
        else serverInfo = auriSession.serverInfoService().update(serverInfo);

        System.out.println("Se guardó: " + serverInfo);

        return "redirect:/cynanthus/dayi/server/" + serverInfo.getId();
    }

    @GetMapping("/instructions")
    public String instructionsView(Model model, HttpServletRequest request) {
        setViewOption(model, "INSTRUCTIONS_VIEW");
        return internalHome(model, request);
    }

    @GetMapping("/users")
    public String usersView(Model model, HttpServletRequest request) {
        setViewOption(model, "USERS_VIEW");
        return internalHome(model, request);
    }

    private void setViewOption(Model model, String viewOption) {
        model.addAttribute("viewOption", viewOption);
    }

}
