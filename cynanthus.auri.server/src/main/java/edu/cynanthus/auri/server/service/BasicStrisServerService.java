package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicStrisServerService extends BasicCynanthusServerService<StrisConfig> implements StrisServerService {

    public BasicStrisServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/stris", StrisConfig.class);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return null;
    }

    @Override
    public List<ControlNode> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
