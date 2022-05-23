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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StrisServerService.STRIS_SERVER_SERVICE_PATH)
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
    @PreAuthorize("hasAnyRole('AGENT','ADMIN','USER')")
    public Boolean performIndication(ServerInfo serverInfo, @RequestBody Indication indication) {
        return strisServerService.performIndication(serverInfo, indication);
    }

    @PostMapping("/{name:" + Patterns.NAME + "}/indication")
    @PreAuthorize("hasAnyRole('ADMIN','USER','USER')")
    public Boolean performIndicationByName(ServerInfo serverInfo, @RequestBody Indication indication) {
        return performIndication(serverInfo, indication);
    }

    @Override
    @GetMapping("/indication/to/{id:\\d+}/with/{instructionName:" + Patterns.NAME + "}")
    public Boolean performIndication(NodeInfo nodeInfo, @PathVariable String instructionName) {
        return strisServerService.performIndication(nodeInfo, instructionName);
    }

    @GetMapping("/indication/to/{name:" + Patterns.MAC + "}/with/{instructionName:" + Patterns.NAME + "}")
    public Boolean performIndicationByName(NodeInfo nodeInfo, @PathVariable String instructionName) {
        return performIndication(nodeInfo, instructionName);
    }

}
