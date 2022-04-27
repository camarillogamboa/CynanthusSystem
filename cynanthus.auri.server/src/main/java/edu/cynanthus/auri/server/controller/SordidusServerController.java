package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.domain.config.SordidusConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cynanthus/auri/server/")
public class SordidusServerController extends CynanthusServerController<SordidusConfig> implements SordidusServerService {

    public SordidusServerController(
        @Qualifier("basicSordidusServerService") SordidusServerService sordidusServerService
    ) {
        super(sordidusServerService);
    }

}
