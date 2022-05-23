package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LatiroServerService.LATIRO_SERVER_SERVICE_PATH)
public class LatiroServerController
    extends TreeServerController<LatiroConfig, SensingNode> implements LatiroServerService {

    @Autowired
    public LatiroServerController(
        @Qualifier("basicLatiroServerService") LatiroServerService latiroServerService
    ) {
        super(latiroServerService);
    }

}
