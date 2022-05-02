package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

class CynanthusServerServiceConsumer<T extends Config>
    extends ConfigurationServerServiceConsumer<T> implements CynanthusServerService<T> {

    CynanthusServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType) {
        super(lazyRequest, resourcePath, configType);
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consumeService(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/available"),
            Boolean.class
        );
    }

}
