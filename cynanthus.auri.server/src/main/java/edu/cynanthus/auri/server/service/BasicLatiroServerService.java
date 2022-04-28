package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * El tipo Basic latiro server service.
 */
@Service
public class BasicLatiroServerService extends BasicCynanthusServerService<LatiroConfig> implements LatiroServerService {

    /**
     * Instancia un nuevo Basic latiro server service.
     *
     * @param serverInfoService el server info service
     */
    @Autowired
    public BasicLatiroServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/latiro", LatiroConfig.class);
    }

    /**
     * Permite obtener sensing nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el sensing nodes of
     */
    @Override
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
