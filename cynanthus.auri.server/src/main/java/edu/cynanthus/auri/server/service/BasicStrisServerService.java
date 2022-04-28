package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * El tipo Basic stris server service.
 */
@Service
public class BasicStrisServerService extends BasicCynanthusServerService<StrisConfig> implements StrisServerService {

    /**
     * Instancia un nuevo Basic stris server service.
     *
     * @param serverInfoService el server info service
     */
    @Autowired
    public BasicStrisServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService, "/cynanthus/stris", StrisConfig.class);
    }

    /**
     * Perform indication boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return null;
    }

    /**
     * Permite obtener control nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el control nodes of
     */
    @Override
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
