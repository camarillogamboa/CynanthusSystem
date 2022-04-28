package edu.cynanthus.auri.api;

import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.List;

/**
 * La interface Stris server service.
 */
public interface StrisServerService extends CynanthusServerService<StrisConfig> {

    /**
     * Perform indication boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    Boolean performIndication(ServerInfo serverInfo, Indication indication);

    /**
     * Permite obtener control nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el control nodes of
     */
    List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector);

}
