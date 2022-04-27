package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LatiroServerController extends CynanthusServerController<LatiroConfig> implements LatiroServerService {

    public LatiroServerController(
        LatiroServerService latiroServerService
    ) {
        super(latiroServerService);
    }

    @Override
    public List<SensingNode> getSensingNodesOf(ServerInfo serverInfo) {
        return null;
    }

}
