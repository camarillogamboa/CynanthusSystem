package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.auri.api.exception.InvalidTypeException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.springframework.util.StringUtils;

import java.net.http.HttpResponse;

class BasicCynanthusServerService<T extends Config> extends MicroServiceConsumer implements CynanthusServerService<T> {

    final ServerInfoService serverInfoService;
    final String basePath;
    final Class<T> configClass;

    final ServerType serverType;

    BasicCynanthusServerService(
        ServerInfoService serverInfoService,
        String basePath,
        Class<T> configClass,
        ServerType serverType
    ) {
        this.serverInfoService = serverInfoService;
        this.basePath = basePath;
        this.configClass = configClass;
        this.serverType = serverType;
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);
        return consume(lazyRequest -> lazyRequest.GET(buildUri(fullServerInfo, "/config")), configClass);
    }

    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        if (serverInfo == null)
            throw new InvalidArgumentException("El objeto de configuración no debe ser nulo");

        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);
        return consume(lazyRequest ->
                lazyRequest.PUT(
                    buildUri(fullServerInfo, "/config"),
                    () -> JsonProvider.toJsonInputStream(config)
                ),
            Boolean.class
        );
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);
        return consume(lazyRequest -> lazyRequest.GET(buildUri(fullServerInfo, "/log/files")), String[].class);
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        if (StringUtils.hasText(logFileName))
            throw new InvalidArgumentException(
                "El nombre del archivo de registro no puede ser nulo, estar vacío o contener espacios en blanco"
            );

        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(buildUri(fullServerInfo, "/log?value=" + logFileName)),
            String.class
        );
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);

        LazyRequest lazyRequest = getLazyRequest();

        lazyRequest.GET(buildUri(fullServerInfo, "/available"));

        try {
            HttpResponse<String> response = lazyRequest.doRequestAndGetString();
            return HttpStatusCode.isCorrect(response.statusCode());
        } catch (Exception ex) {
            return false;
        }
    }

    void checkServerType(ServerInfo serverInfo) {
        if (!serverInfo.getServerType().equals(serverType))
            throw new InvalidTypeException(
                "Este servicio es únicamente para objetos ServerInfo de tipo "
                    + serverType + " y el ServerInfo \"" + serverInfo.getName() + "\" no es de ese tipo"
            );
    }

    String buildUri(ServerInfo serverInfo, String subPath) {
        return "http://" + serverInfo.getAddress() + ":" + serverInfo.getPort() + basePath + subPath;
    }

}
