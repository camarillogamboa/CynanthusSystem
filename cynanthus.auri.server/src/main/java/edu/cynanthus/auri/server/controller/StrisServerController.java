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

/**
 * El tipo Stris server controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/server")
public class StrisServerController extends CynanthusServerController<StrisConfig> implements StrisServerService {

    /**
     * El Stris server service.
     */
    private final StrisServerService strisServerService;

    /**
     * Instancia un nuevo Stris server controller.
     *
     * @param strisServerService el stris server service
     */
    @Autowired
    public StrisServerController(
        @Qualifier("basicStrisServerService") StrisServerService strisServerService
    ) {
        super(strisServerService);
        this.strisServerService = strisServerService;
    }

    /**
     * Permite obtener control nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el control nodes of
     */
    @Override
    @GetMapping("{id:\\d+}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return strisServerService.getControlNodesOf(serverInfo, selector);
    }

    /**
     * Permite obtener control nodes of by name.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el control nodes of by name
     */
    @GetMapping("{name:" + Patterns.NAME + "}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<ControlNode>> getControlNodesOfByName(ServerInfo serverInfo, @PathVariable String selector) {
        return getControlNodesOf(serverInfo, selector);
    }

    /**
     * Perform indication boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    @Override
    @PutMapping("/indication/{id:\\d+}")
    @ResponseBody
    public Boolean performIndication(ServerInfo serverInfo, @RequestBody Indication indication) {
        return strisServerService.performIndication(serverInfo, indication);
    }

    /**
     * Perform indication by name boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    @PutMapping("/indication/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Boolean performIndicationByName(ServerInfo serverInfo, @RequestBody Indication indication) {
        return performIndication(serverInfo, indication);
    }

}
