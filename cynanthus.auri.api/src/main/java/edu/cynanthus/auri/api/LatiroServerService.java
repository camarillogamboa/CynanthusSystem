package edu.cynanthus.auri.api;

import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.List;

public interface LatiroServerService extends CynanthusServerService<LatiroConfig> {

    List<SensingNode> getSensingNodesOf(ServerInfo serverInfo);

}
