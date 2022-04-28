package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * El tipo Latiro server controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/server")
public class LatiroServerController extends CynanthusServerController<LatiroConfig> implements LatiroServerService {

    /**
     * El Latiro server service.
     */
    private final LatiroServerService latiroServerService;

    /**
     * Instancia un nuevo Latiro server controller.
     *
     * @param latiroServerService el latiro server service
     */
    @Autowired
    public LatiroServerController(
        @Qualifier("basicLatiroServerService") LatiroServerService latiroServerService
    ) {
        super(latiroServerService);
        this.latiroServerService = latiroServerService;
    }

    /**
     * Permite obtener sensing nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el sensing nodes of
     */
    @Override
    @GetMapping("/{id:\\d+}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        return latiroServerService.getSensingNodesOf(serverInfo, selector);
    }

    /**
     * Permite obtener sensing nodes of by name.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el sensing nodes of by name
     */
    @GetMapping("/{name:" + Patterns.NAME + "}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<SensingNode>> getSensingNodesOfByName(ServerInfo serverInfo, String selector) {
        return getSensingNodesOf(serverInfo, selector);
    }


}
