package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

/**
 * El tipo Configuration server service consumer.
 *
 * @param <T> el parámetro de tipo
 */
class ConfigurationServerServiceConsumer<T extends Config>
    extends ServiceConsumer implements ConfigurationServerService<T> {

    /**
     * El Resource path.
     */
    final String resourcePath;

    /**
     * El Config type.
     */
    private final Type configType;

    /**
     * Instancia un nuevo Configuration server service consumer.
     *
     * @param lazyRequest  el lazy request
     * @param resourcePath el resource path
     * @param configType   el config type
     */
    ConfigurationServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType) {
        super(lazyRequest);
        this.resourcePath = resourcePath;
        this.configType = configType;
    }

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/config"),
            configType
        );
    }

    /**
     * Update config of boolean.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el boolean
     */
    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        checkServerInfo(serverInfo);
        checkConfig(config);
        return sendAndConsume(
            lazyRequest -> lazyRequest.PUT(
                resourcePath + "/" + getServerId(serverInfo) + "/config",
                () -> JsonProvider.toJsonInputStream(config)
            ),
            Boolean.class
        );
    }

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/log"),
            String[].class
        );
    }

    /**
     * Permite obtener log content of.
     *
     * @param serverInfo  el server info
     * @param logFileName el log file name
     * @return el log content of
     */
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

    /**
     * Check config.
     *
     * @param config el config
     */
    void checkConfig(T config) {
        if (config == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de " + configType + " para realizar esta acción"
            );
    }

    /**
     * Check server info.
     *
     * @param serverInfo el server info
     */
    void checkServerInfo(ServerInfo serverInfo) {
        if (serverInfo == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de ServerInfo para realizar esta acción"
            );
    }

    /**
     * Permite obtener server id.
     *
     * @param bean el bean
     * @return el server id
     */
    Object getServerId(ServerInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido del ServerInfo para realizar esta acción"
            );
    }

}
