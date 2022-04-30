package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

class ConfigurationServerServiceConsumer<T extends Config>
    extends AuriApiConsumer implements ConfigurationServerService<T> {

    protected final String resourcePath;

    private final Type configType;

    ConfigurationServerServiceConsumer(ClientInfo clientInfo, String resourcePath, Type configType) {
        super(clientInfo);
        this.resourcePath = resourcePath;
        this.configType = configType;
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consumeApi(
            webConsumer -> webConsumer.GET(resourcePath + "/" + getServerId(serverInfo) + "/config"),
            configType
        );
    }

    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        checkServerInfo(serverInfo);
        checkConfig(config);
        return consumeApi(
            webConsumer -> webConsumer.PUT(
                resourcePath + "/" + getServerId(serverInfo) + "/config",
                () -> StreamUtil.asInputStream(JsonProvider.toJson(config))
            ),
            Boolean.class
        );
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consumeApi(
            webConsumer -> webConsumer.GET(resourcePath + "/" + getServerId(serverInfo) + "/log"),
            String[].class
        );
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        checkServerInfo(serverInfo);
        if (logFileName == null)
            throw new ServiceException(
                "Se requiere un nombre de archivo de registro",
                ExceptionType.REQUIRED_DATA
            );

        return consumeApi(
            webConsumer -> webConsumer.GET(resourcePath + "/" + getServerId(serverInfo) + "/log/" + logFileName),
            String.class
        );
    }

    void checkConfig(T config) {
        if (config == null)
            throw new ServiceException(
                "Se requiere una instancia de " + configType + " para realizar esta acción",
                ExceptionType.NULL_POINTER
            );
    }

    void checkServerInfo(ServerInfo serverInfo) {
        if (serverInfo == null)
            throw new ServiceException(
                "Se requiere una instancia de ServerInfo para realizar esta acción",
                ExceptionType.NULL_POINTER
            );
    }

    Object getServerId(ServerInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new ServiceException(
                "Se requiere un identificador válido del ServerInfo para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );
    }

}
