package edu.cynanthus.auri.api;

import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

/**
 * La interface Stris server service.
 */
public interface StrisServerService extends TreeServerService<StrisConfig, ControlNode> {

    /**
     * Perform indication boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    Boolean performIndication(ServerInfo serverInfo, Indication indication);

    /**
     * Perform indication boolean.
     *
     * @param nodeInfo        el node info
     * @param instructionName el instruction name
     * @return el boolean
     */
    Boolean performIndication(NodeInfo nodeInfo, String instructionName);

}
