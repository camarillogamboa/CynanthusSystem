package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.domain.*;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicStrisServerService extends BasicCynanthusServerService<StrisConfig> implements StrisServerService {

    @Autowired
    public BasicStrisServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/stris", StrisConfig.class, ServerType.CONTROL);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return null;
    }

    @Override
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
