package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicLatiroServerService extends BasicCynanthusServerService<LatiroConfig> implements LatiroServerService {

    @Autowired
    public BasicLatiroServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/latiro", LatiroConfig.class);
    }

    @Override
    public List<SensingNode> getSensingNodesOf(ServerInfo serverInfo) {
        return null;
    }

}
