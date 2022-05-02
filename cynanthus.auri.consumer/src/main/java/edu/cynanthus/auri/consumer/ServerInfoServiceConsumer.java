package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;

class ServerInfoServiceConsumer extends BeanServiceConsumer<ServerInfo> implements ServerInfoService {
    ServerInfoServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            "/cynanthus/auri/server",
            ServerInfo.class,
            new TypeToken<List<ServerInfo>>() {}.getType()
        );
    }

    @Override
    Object getId(ServerInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new ServiceException(
                "Se requiere un identificador válido para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );
    }

}
