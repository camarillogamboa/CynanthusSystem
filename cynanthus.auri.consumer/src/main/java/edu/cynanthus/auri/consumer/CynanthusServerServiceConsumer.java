package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

class CynanthusServerServiceConsumer<T extends Config>
    extends ConfigurationServerServiceConsumer<T> implements CynanthusServerService<T> {

    CynanthusServerServiceConsumer(ClientInfo clientInfo, String resourcePath, Type configType) {
        super(clientInfo, resourcePath, configType);
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consumeApi(
            webConsumer -> webConsumer.GET(resourcePath + "/" + getServerId(serverInfo) + "/available"),
            Boolean.class
        );
    }

}
