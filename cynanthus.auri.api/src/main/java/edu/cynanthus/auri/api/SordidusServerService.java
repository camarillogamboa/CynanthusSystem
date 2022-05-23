package edu.cynanthus.auri.api;

import edu.cynanthus.domain.config.SordidusConfig;

/**
 * La interface Sordidus server service.
 */
public interface SordidusServerService extends CynanthusServerService<SordidusConfig> {

    /**
     * La constante SORDIDUS_SERVER_SERVICE_PATH.
     */
    String SORDIDUS_SERVER_SERVICE_PATH = CYNANTHUS_SERVER_SERVICE_PATH + "/sordidus";

}
