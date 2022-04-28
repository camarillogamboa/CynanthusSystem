package edu.cynanthus.auri.api;

import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.List;

/**
 * La interface Latiro server service.
 */
public interface LatiroServerService extends CynanthusServerService<LatiroConfig> {

    /**
     * Permite obtener sensing nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el sensing nodes of
     */
    List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector);

}
