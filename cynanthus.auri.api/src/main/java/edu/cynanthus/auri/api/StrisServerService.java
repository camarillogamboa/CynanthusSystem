package edu.cynanthus.auri.api;

import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.List;

public interface StrisServerService extends CynanthusServerService<StrisConfig> {

    Boolean performIndication(ServerInfo serverInfo, Indication indication);

    List<ControlNode> getControlNodesOf(ServerInfo serverInfo, String selector);

}
