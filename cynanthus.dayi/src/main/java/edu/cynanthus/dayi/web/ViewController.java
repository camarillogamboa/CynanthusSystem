package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.auri.consumer.AuriServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cynanthus/dayi")
public class ViewController {

    private final AuriServiceConsumer<AuthService> authServiceConsumer;
    private final AuriServiceConsumer<ServerInfoService> serverInfoServiceConsumer;
    private final AuriServiceConsumer<NodeInfoService> nodeInfoServiceConsumer;
    private final AuriServiceConsumer<InstructionSetService> instructionSetserviceConsumer;
    private final AuriServiceConsumer<UserService> userServiceConsumer;
    private final AuriServiceConsumer<SordidusServerService> sordidusServerServiceConsumer;
    private final AuriServiceConsumer<LatiroServerService> latiroServerServiceConsumer;
    private final AuriServiceConsumer<StrisServerService> strisServerServiceConsumer;

    @Autowired
    public ViewController(
        AuriServiceConsumer<AuthService> authServiceConsumer,
        AuriServiceConsumer<ServerInfoService> serverInfoServiceConsumer,
        AuriServiceConsumer<NodeInfoService> nodeInfoServiceConsumer,
        AuriServiceConsumer<InstructionSetService> instructionSetserviceConsumer,
        AuriServiceConsumer<UserService> userServiceConsumer,
        AuriServiceConsumer<SordidusServerService> sordidusServerServiceConsumer,
        AuriServiceConsumer<LatiroServerService> latiroServerServiceConsumer,
        AuriServiceConsumer<StrisServerService> strisServerServiceConsumer
    ) {
        this.authServiceConsumer = authServiceConsumer;
        this.serverInfoServiceConsumer = serverInfoServiceConsumer;
        this.nodeInfoServiceConsumer = nodeInfoServiceConsumer;
        this.instructionSetserviceConsumer = instructionSetserviceConsumer;
        this.userServiceConsumer = userServiceConsumer;
        this.sordidusServerServiceConsumer = sordidusServerServiceConsumer;
        this.latiroServerServiceConsumer = latiroServerServiceConsumer;
        this.strisServerServiceConsumer = strisServerServiceConsumer;
    }

    @GetMapping
    public String index(Model model) {

        return "index";
    }

}
