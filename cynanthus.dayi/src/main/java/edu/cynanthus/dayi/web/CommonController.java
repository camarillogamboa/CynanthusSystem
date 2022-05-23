package edu.cynanthus.dayi.web;

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

}
