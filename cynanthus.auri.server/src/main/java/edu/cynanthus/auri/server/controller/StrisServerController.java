package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cynanthus/auri/server/stris")
public class StrisServerController extends CynanthusServerController<StrisConfig> implements StrisServerService {

    private final StrisServerService strisServerService;

    @Autowired
    public StrisServerController(
        @Qualifier("basicStrisServerService") StrisServerService strisServerService
    ) {
        super(strisServerService);
        this.strisServerService = strisServerService;
    }

    @Override
    @GetMapping("{id:\\d+}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return strisServerService.getControlNodesOf(serverInfo, selector);
    }

    @GetMapping("{name:" + Patterns.NAME + "}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<ControlNode>> getControlNodesOfByName(ServerInfo serverInfo, @PathVariable String selector) {
        return getControlNodesOf(serverInfo, selector);
    }

    @Override
    @PutMapping("/indication/{id:\\d+}")
    @ResponseBody
    public Boolean performIndication(ServerInfo serverInfo, @RequestBody Indication indication) {
        return strisServerService.performIndication(serverInfo, indication);
    }

    @PutMapping("/indication/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Boolean performIndicationByName(ServerInfo serverInfo, @RequestBody Indication indication) {
        return performIndication(serverInfo, indication);
    }

}
