package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.Required;
import edu.cynanthus.dayi.util.BeanUtil;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
        List<? extends ServerInfo> serverInfos = auriSession.serverInfoService().read();
        model.addAttribute("categories", BeanUtil.toCategories(serverInfos));

        Object currentPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        System.out.println(currentPath);
        model.addAttribute("currentPath", currentPath);

        return "index";
    }

    @GetMapping("/server/{id:\\d+}")
    public String serverView(ServerInfo serverInfo, Model model, HttpServletRequest request) {
        serverInfo = auriSession.serverInfoService().read(serverInfo);

        model.addAttribute("currentServer", serverInfo);

        return home(model, request);
    }

    @PostMapping("/server")
    public String addServer(
        ServerInfo serverInfo,
        @RequestParam(required = false) String previus
    ) {
        serverInfo= auriSession.serverInfoService().create(serverInfo);
        return previus != null ? "redirect:" + previus : "/cynanthus/dayi";
    }

    @GetMapping("/instructions")
    public String instructions(Model model, HttpServletRequest request) {

        List<? extends InstructionSet> instructionSets = auriSession.instructionSetService().read();

        return home(model, request);
    }

}
