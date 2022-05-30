package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.domain.ServerType;
import edu.cynanthus.domain.User;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;

/**
 * La interface Auri session.
 */
public interface AuriSession extends SessionManager {
    /**
     * Permite obtener time out.
     *
     * @return el time out
     */
    Duration getTimeOut();

    /**
     * Permite establecer time out.
     *
     * @param timeOut el time out
     */
    void setTimeOut(Duration timeOut);

    /**
     * Server info service server info service.
     *
     * @param headers el headers
     * @return el server info service
     */
    ServerInfoService serverInfoService(Map<String, String> headers);

    /**
     * Server info service server info service.
     *
     * @return el server info service
     */
    ServerInfoService serverInfoService();

    /**
     * Node info service node info service.
     *
     * @param headers el headers
     * @return el node info service
     */
    NodeInfoService nodeInfoService(Map<String, String> headers);

    /**
     * Node info service node info service.
     *
     * @return el node info service
     */
    NodeInfoService nodeInfoService();

    /**
     * Instruction set service instruction set service.
     *
     * @param headers el headers
     * @return el instruction set service
     */
    InstructionSetService instructionSetService(Map<String, String> headers);

    /**
     * Instruction set service instruction set service.
     *
     * @return el instruction set service
     */
    InstructionSetService instructionSetService();

    /**
     * User service user service.
     *
     * @param headers el headers
     * @return el user service
     */
    UserService userService(Map<String, String> headers);

    /**
     * User service user service.
     *
     * @return el user service
     */
    UserService userService();

    /**
     * Cynanthus server service cynanthus server service.
     *
     * @param <T>        el parámetro de tipo
     * @param serverType el server type
     * @return el cynanthus server service
     */
    <T extends Config> CynanthusServerService<T> cynanthusServerService(ServerType serverType);

    /**
     * Cynanthus server service cynanthus server service.
     *
     * @param <T>        el parámetro de tipo
     * @param serverType el server type
     * @param headers    el headers
     * @return el cynanthus server service
     */
    <T extends Config> CynanthusServerService<T> cynanthusServerService(
        ServerType serverType,
        Map<String, String> headers
    );

    /**
     * Sordidus server service sordidus server service.
     *
     * @param headers el headers
     * @return el sordidus server service
     */
    SordidusServerService sordidusServerService(Map<String, String> headers);

    /**
     * Sordidus server service sordidus server service.
     *
     * @return el sordidus server service
     */
    SordidusServerService sordidusServerService();

    /**
     * Latiro server service latiro server service.
     *
     * @param headers el headers
     * @return el latiro server service
     */
    LatiroServerService latiroServerService(Map<String, String> headers);

    /**
     * Latiro server service latiro server service.
     *
     * @return el latiro server service
     */
    LatiroServerService latiroServerService();

    /**
     * Stris server service stris server service.
     *
     * @param headers el headers
     * @return el stris server service
     */
    StrisServerService strisServerService(Map<String, String> headers);

    /**
     * Stris server service stris server service.
     *
     * @return el stris server service
     */
    StrisServerService strisServerService();

    /**
     * Create renewable session auri session.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @param user        el user
     * @return el auri session
     */
    static AuriSession createRenewableSession(HttpClient httpClient, HostAddress hostAddress, User user) {
        return new RenewableAuriSession(httpClient, hostAddress, user);
    }

    /**
     * Create renewable session auri session.
     *
     * @param hostAddress el host address
     * @param user        el user
     * @return el auri session
     */
    static AuriSession createRenewableSession(HostAddress hostAddress, User user) {
        return createRenewableSession(HttpClient.newHttpClient(), hostAddress, user);
    }

}
