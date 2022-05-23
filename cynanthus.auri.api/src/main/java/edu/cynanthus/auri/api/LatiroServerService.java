package edu.cynanthus.auri.api;

import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;

/**
 * La interface Latiro server service.
 */
public interface LatiroServerService extends TreeServerService<LatiroConfig, SensingNode> {

    String LATIRO_SERVER_SERVICE_PATH = CYNANTHUS_SERVER_SERVICE_PATH + "/latiro";

}
