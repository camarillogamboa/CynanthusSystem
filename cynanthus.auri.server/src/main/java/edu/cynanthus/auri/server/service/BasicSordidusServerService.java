package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.domain.config.SordidusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BasicSordidusServerService
    extends BasicCynanthusServerService<SordidusConfig> implements SordidusServerService {

    @Autowired
    public BasicSordidusServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/sordidus", SordidusConfig.class);
    }

}
