package edu.cynanthus.auri.api;

import edu.cynanthus.domain.ServerInfo;

/**
 * La interface Server info service.
 */
public interface ServerInfoService extends BeanService<ServerInfo> {

    String SERVER_INFO_SERVICE_PATH = AURI_PATH + "/server";

}
