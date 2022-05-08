package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

class ConfigurationServerServiceConsumer<T extends Config>
    extends GeneralConsumer implements ConfigurationServerService<T> {

    final String resourcePath;

    private final Type configType;

    ConfigurationServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType) {
        super(lazyRequest);
        this.resourcePath = resourcePath;
        this.configType = configType;
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/config"),
            configType
        );
    }

    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        checkServerInfo(serverInfo);
        checkConfig(config);
        return consume(
            lazyRequest -> lazyRequest.PUT(
                resourcePath + "/" + getServerId(serverInfo) + "/config",
                () -> JsonProvider.toJsonInputStream(config)
            ),
            Boolean.class
        );
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/log"),
            String[].class
        );
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        checkServerInfo(serverInfo);
        if (logFileName == null)
            throw new InvalidArgumentException("Se requiere un nombre de archivo de registro");

        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/log/" + logFileName),
            String.class
        );
    }

    void checkConfig(T config) {
        if (config == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de " + configType + " para realizar esta acción"
            );
    }

    void checkServerInfo(ServerInfo serverInfo) {
        if (serverInfo == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de ServerInfo para realizar esta acción"
            );
    }

    Object getServerId(ServerInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido del ServerInfo para realizar esta acción"
            );
    }

}
