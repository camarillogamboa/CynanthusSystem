package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

class ConfigurationServerServiceConsumer<T extends Config>
    extends AuriApiConsumer implements ConfigurationServerService<T> {

    private final Type configType;

    private ConfigurationServerServiceConsumer(ClientInfo clientInfo, Type configType) {
        super(clientInfo);
        this.configType = configType;
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return null;
    }

    @Override
    public String updateConfigOf(ServerInfo serverInfo, T config) {
        checkServerInfo(serverInfo);
        checkConfig(config);
        return null;
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return new String[0];
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return null;
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
