package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.domain.ServerInfo;

class CommonController {

    AuriSession auriSession;

    CommonController(AuriSession auriSession) {
        this.auriSession = auriSession;
    }

    ServerInfo findServerInfo(ServerInfo serverInfo) {
        return auriSession.serverInfoService().read(serverInfo);
    }

    Boolean isAvailable(CynanthusServerService<?> cynanthusServerService, ServerInfo serverInfo) {
        return cynanthusServerService.isAvailable(serverInfo);
    }

    Boolean isAvailable(ServerInfo serverInfo) {
        return isAvailable(auriSession.cynanthusServerService(serverInfo.getServerType()), serverInfo);
    }

}
