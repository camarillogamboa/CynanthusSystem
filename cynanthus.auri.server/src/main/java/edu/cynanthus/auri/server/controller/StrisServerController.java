package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cynanthus/auri/server/stris")
public class StrisServerController
    extends TreeServerController<StrisConfig, ControlNode> implements StrisServerService {

    private final StrisServerService strisServerService;

    @Autowired
    public StrisServerController(
        @Qualifier("basicStrisServerService") StrisServerService strisServerService
    ) {
        super(strisServerService);
        this.strisServerService = strisServerService;
    }

    @Override
    @PostMapping("/{id:\\d+}/indication")
    @ResponseBody
    public Boolean performIndication(@PathVariable("id") ServerInfo serverInfo, @RequestBody Indication indication) {
        return strisServerService.performIndication(serverInfo, indication);
    }

    @PostMapping("/{name:" + Patterns.NAME + "}/indication/")
    @ResponseBody
    public Boolean performIndicationByName(
        @PathVariable("name") ServerInfo serverInfo,
        @RequestBody Indication indication
    ) {
        return performIndication(serverInfo, indication);
    }

    @Override
    @GetMapping("/indication/to/{id:\\d+}/with/{instructionName:" + Patterns.NAME + "}")
    @ResponseBody
    public Boolean performIndication(@PathVariable("id") NodeInfo nodeInfo, @PathVariable String instructionName) {
        return strisServerService.performIndication(nodeInfo, instructionName);
    }

    @GetMapping("/indication/to/{name:" + Patterns.MAC + "}/with/{instructionName:" + Patterns.NAME + "}")
    @ResponseBody
    public Boolean performIndicationByName(@PathVariable("name") NodeInfo nodeInfo, String instructionName) {
        return performIndication(nodeInfo, instructionName);
    }

}
