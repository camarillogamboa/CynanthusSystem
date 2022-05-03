package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.error.InvalidDataException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;
import java.util.List;

class ServerInfoServiceConsumer extends BeanServiceConsumer<ServerInfo> implements ServerInfoService {

    private static final Type SERVER_INFO_LIST_TYPE = new TypeToken<List<ServerInfo>>() {}.getType();

    ServerInfoServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            "/cynanthus/auri/server",
            ServerInfo.class,
            SERVER_INFO_LIST_TYPE
        );
    }

    @Override
    Object getId(ServerInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new InvalidDataException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
