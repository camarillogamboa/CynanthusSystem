package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.SSV;
import edu.cynanthus.common.net.http.client.JsonBodyHandler;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.common.security.Encryption;
import edu.cynanthus.common.security.SystemUser;
import edu.cynanthus.domain.ServerInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * El tipo Basic cynanthus server service.
 *
 * @param <T> el parámetro de tipo
 */
class BasicCynanthusServerService<T extends Config> implements CynanthusServerService<T> {

    /**
     * La constante PATH_PREFIX.
     */
    private static final String PATH_PREFIX = "http://";

    /**
     * El Server info service.
     */
    private final ServerInfoService serverInfoService;
    /**
     * El Root path.
     */
    private final String rootPath;

    /**
     * El Config class.
     */
    private final Class<T> configClass;

    /**
     * El Http client.
     */
    final HttpClient httpClient;

    /**
     * El Request user.
     */
    final SystemUser requestUser;

    /**
     * El Headers.
     */
    private final Map<String, String[]> headers;

    /**
     * Instancia un nuevo Basic cynanthus server service.
     *
     * @param serverInfoService el server info service
     * @param rootPath          el root path
     * @param configClass       el config class
     */
    BasicCynanthusServerService(ServerInfoService serverInfoService, String rootPath, Class<T> configClass) {
        this.serverInfoService = Objects.requireNonNull(serverInfoService);
        this.rootPath = Objects.requireNonNull(rootPath);
        this.configClass = Objects.requireNonNull(configClass);

        this.httpClient = HttpClient.newHttpClient();
        this.requestUser = AgentUser.DEFAUL_AGENT_USER;
        this.headers = new TreeMap<>(String::compareTo);

        String[] credentials = requestUser.getCredentials();

        try {
            credentials[1] = Encryption.decrypt(credentials[1]);
        } catch (GeneralSecurityException e) {
        }

        headers.put("Credentials", credentials);
    }

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        serverInfo = serverInfoService.read(serverInfo);
        System.out.println(serverInfo);

        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder = builder.uri(URI.create(buildPath(serverInfo, "/config?value=hola")));

        builder = insertHeaders(builder);

        HttpRequest request = builder.GET().build();

        HttpResponse.BodyHandler<T> jsonBodyHandler = new JsonBodyHandler<>(configClass);

        try {
            return httpClient.send(request, jsonBodyHandler).body();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("Error de IO en comunicación con servidor Cynanthus", ExceptionType.IO);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new ServiceException(
                "Iterrupción del proceso de comunicación con el servidor Cynanthus",
                ExceptionType.INTERRUPTED
            );
        }
    }

    /**
     * Update config of string.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el string
     */
    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, Config config) {
        serverInfo = serverInfoService.read(serverInfo);
        return null;
    }

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        serverInfo = serverInfoService.read(serverInfo);

        return new String[0];
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
        serverInfo = serverInfoService.read(serverInfo);

        return null;
    }

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        serverInfo = serverInfoService.read(serverInfo);

        return false;
    }

    /**
     * Build path string.
     *
     * @param serverInfo el server info
     * @param subPath    el sub path
     * @return el string
     */
    String buildPath(ServerInfo serverInfo, String subPath) {
        return PATH_PREFIX + serverInfo.getAddress() + ":" + serverInfo.getPort() + rootPath + subPath;
    }

    /**
     * Insert headers http request . builder.
     *
     * @param builder el builder
     * @return el http request . builder
     */
    HttpRequest.Builder insertHeaders(HttpRequest.Builder builder) {
        for (Map.Entry<String, String[]> header : headers.entrySet()) {
            builder = builder.header(header.getKey(), SSV.toSSVFormat(header.getValue(), " "));
        }
        return builder;
    }

}
