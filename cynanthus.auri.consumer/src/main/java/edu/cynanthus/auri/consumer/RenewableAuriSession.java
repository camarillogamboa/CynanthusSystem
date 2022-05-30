package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.ServerType;
import edu.cynanthus.domain.User;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * El tipo Renewable auri session.
 */
class RenewableAuriSession implements AuriSession {

    /**
     * El Time out.
     */
    private Duration timeOut;

    /**
     * El Request builder.
     */
    private final Function<Map<String, String>, Consumer<LazyRequest>> requestBuilder;

    /**
     * El Session manager.
     */
    private final SessionManager sessionManager;
    /**
     * El Server info service consumer.
     */
    private final AuriServiceConsumer<ServerInfoService> serverInfoServiceConsumer;
    /**
     * El Node info service consumer.
     */
    private final AuriServiceConsumer<NodeInfoService> nodeInfoServiceConsumer;
    /**
     * El Instruction set service consumer.
     */
    private final AuriServiceConsumer<InstructionSetService> instructionSetServiceConsumer;
    /**
     * El User service consumer.
     */
    private final AuriServiceConsumer<UserService> userServiceConsumer;
    /**
     * El Sordidus server service consumer.
     */
    private final AuriServiceConsumer<SordidusServerService> sordidusServerServiceConsumer;
    /**
     * El Latiro server service consumer.
     */
    private final AuriServiceConsumer<LatiroServerService> latiroServerServiceConsumer;
    /**
     * El Stris server service consumer.
     */
    private final AuriServiceConsumer<StrisServerService> strisServerServiceConsumer;

    /**
     * El Default server info service.
     */
    private final ServerInfoService defaultServerInfoService;
    /**
     * El Default node info service.
     */
    private final NodeInfoService defaultNodeInfoService;
    /**
     * El Default instruction set service.
     */
    private final InstructionSetService defaultInstructionSetService;
    /**
     * El Default user service.
     */
    private final UserService defaultUserService;
    /**
     * El Default sordidus server service.
     */
    private final SordidusServerService defaultSordidusServerService;
    /**
     * El Default latiro server service.
     */
    private final LatiroServerService defaultLatiroServerService;
    /**
     * El Default stris server service.
     */
    private final StrisServerService defaultStrisServerService;

    /**
     * Instancia un nuevo Renewable auri session.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @param user        el user
     */
    RenewableAuriSession(HttpClient httpClient, HostAddress hostAddress, User user) {
        Objects.requireNonNull(hostAddress);
        Objects.requireNonNull(user);

        this.timeOut = Duration.ofSeconds(5);

        Consumer<LazyRequest> timeOutSetter
            = lazyRequest -> lazyRequest.building(builder -> builder.timeout(getTimeOut()));

        this.requestBuilder
            = headers -> timeOutSetter.andThen(lazyRequest -> lazyRequest.addHeaders(prepareHeaders(headers)));

        AuriServiceConsumer<AuthService> authServiceConsumer = AuriServiceConsumer.createAuthServiceConsumer(
            httpClient,
            hostAddress
        );

        this.sessionManager = new SessionManagerImpl(user, authServiceConsumer.prepare());

        this.serverInfoServiceConsumer = AuriServiceConsumer.createServerInfoServiceConsumer(httpClient, hostAddress);
        this.nodeInfoServiceConsumer = AuriServiceConsumer.createNodeInfoServiceConsumer(httpClient, hostAddress);
        this.instructionSetServiceConsumer = AuriServiceConsumer.createInstructionSetServiceConsumer(httpClient, hostAddress);
        this.userServiceConsumer = AuriServiceConsumer.createUserServiceConsumer(httpClient, hostAddress);
        this.sordidusServerServiceConsumer = AuriServiceConsumer.createSordidusServerServiceConsumer(httpClient, hostAddress);
        this.latiroServerServiceConsumer = AuriServiceConsumer.createLatiroServerServiceConsumer(httpClient, hostAddress);
        this.strisServerServiceConsumer = AuriServiceConsumer.createStrisServerServiceConsumer(httpClient, hostAddress);

        Consumer<LazyRequest> consumerLazyRequest
            = timeOutSetter.andThen(lazyRequest -> lazyRequest.addHeaders(prepareDeafultHeaders()));

        this.defaultServerInfoService = new SessionBasedServerInfoService(serverInfoServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultNodeInfoService = new SessionBasedNodeInfoService(nodeInfoServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultInstructionSetService = new SessionBasedInstructionSetService(instructionSetServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultUserService = new SessionBasedUserService(userServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultSordidusServerService = new SessionBasedSordidusServerService(sordidusServerServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultLatiroServerService = new SessionBasedLatiroServerService(latiroServerServiceConsumer, sessionManager, consumerLazyRequest);
        this.defaultStrisServerService = new SessionBasedStrisServerService(strisServerServiceConsumer, sessionManager, consumerLazyRequest);
    }

    /**
     * Permite obtener time out.
     *
     * @return el time out
     */
    @Override
    public Duration getTimeOut() {
        return timeOut;
    }

    /**
     * Permite establecer time out.
     *
     * @param timeOut el time out
     */
    @Override
    public void setTimeOut(Duration timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Permite obtener authenticated user.
     *
     * @return el authenticated user
     */
    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return sessionManager.getAuthenticatedUser();
    }

    /**
     * Login.
     */
    @Override
    public void login() {
        sessionManager.login();
    }

    /**
     * Logout.
     */
    @Override
    public void logout() {
        sessionManager.logout();
    }

    /**
     * Prepare deafult headers map.
     *
     * @return el map
     */
    private Map<String, String> prepareDeafultHeaders() {
        Map<String, String> headers = new TreeMap<>(String::compareTo);
        String token = "";

        AuthenticatedUser authenticatedUser = sessionManager.getAuthenticatedUser();

        if (authenticatedUser != null && authenticatedUser.getToken() != null)
            token = authenticatedUser.getToken();

        headers.put("Authorization", "Bearer " + token);
        return headers;
    }

    /**
     * Prepare headers map.
     *
     * @param externalHeaders el external headers
     * @return el map
     */
    private Map<String, String> prepareHeaders(Map<String, String> externalHeaders) {
        Map<String, String> headers = prepareDeafultHeaders();
        if (externalHeaders != null)
            headers.putAll(externalHeaders);
        return headers;
    }

    /**
     * Server info service server info service.
     *
     * @param headers el headers
     * @return el server info service
     */
    @Override
    public ServerInfoService serverInfoService(Map<String, String> headers) {
        return new SessionBasedServerInfoService(
            serverInfoServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    /**
     * Server info service server info service.
     *
     * @return el server info service
     */
    @Override
    public ServerInfoService serverInfoService() {
        return defaultServerInfoService;
    }

    /**
     * Node info service node info service.
     *
     * @param headers el headers
     * @return el node info service
     */
    @Override
    public NodeInfoService nodeInfoService(Map<String, String> headers) {
        return new SessionBasedNodeInfoService(nodeInfoServiceConsumer, sessionManager, requestBuilder.apply(headers));
    }

    /**
     * Node info service node info service.
     *
     * @return el node info service
     */
    @Override
    public NodeInfoService nodeInfoService() {
        return defaultNodeInfoService;
    }

    /**
     * Instruction set service instruction set service.
     *
     * @param headers el headers
     * @return el instruction set service
     */
    @Override
    public InstructionSetService instructionSetService(Map<String, String> headers) {
        return new SessionBasedInstructionSetService(
            instructionSetServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    /**
     * Instruction set service instruction set service.
     *
     * @return el instruction set service
     */
    @Override
    public InstructionSetService instructionSetService() {
        return defaultInstructionSetService;
    }

    /**
     * User service user service.
     *
     * @param headers el headers
     * @return el user service
     */
    @Override
    public UserService userService(Map<String, String> headers) {
        return new SessionBasedUserService(userServiceConsumer, sessionManager, requestBuilder.apply(headers));
    }

    /**
     * User service user service.
     *
     * @return el user service
     */
    @Override
    public UserService userService() {
        return defaultUserService;
    }

    /**
     * Cynanthus server service cynanthus server service.
     *
     * @param <T>        el parámetro de tipo
     * @param serverType el server type
     * @return el cynanthus server service
     */
    @Override
    public <T extends Config> CynanthusServerService<T> cynanthusServerService(ServerType serverType) {
        switch (serverType) {
            case STORAGE:
                return (CynanthusServerService<T>) sordidusServerService();
            case STREAM_DATA:
                return (CynanthusServerService<T>) latiroServerService();
            case CONTROL:
                return (CynanthusServerService<T>) strisServerService();
            default:
                throw new InvalidArgumentException("Tipo enumerado inválido");
        }
    }

    /**
     * Cynanthus server service cynanthus server service.
     *
     * @param <T>        el parámetro de tipo
     * @param serverType el server type
     * @param headers    el headers
     * @return el cynanthus server service
     */
    @Override
    public <T extends Config> CynanthusServerService<T> cynanthusServerService(
        ServerType serverType,
        Map<String, String> headers
    ) {
        switch (serverType) {
            case STORAGE:
                return (CynanthusServerService<T>) sordidusServerService(headers);
            case STREAM_DATA:
                return (CynanthusServerService<T>) latiroServerService(headers);
            case CONTROL:
                return (CynanthusServerService<T>) strisServerService(headers);
            default:
                throw new InvalidArgumentException("Tipo enumerado inválido");
        }
    }

    /**
     * Sordidus server service sordidus server service.
     *
     * @param headers el headers
     * @return el sordidus server service
     */
    @Override
    public SordidusServerService sordidusServerService(Map<String, String> headers) {
        return new SessionBasedSordidusServerService(
            sordidusServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    /**
     * Sordidus server service sordidus server service.
     *
     * @return el sordidus server service
     */
    @Override
    public SordidusServerService sordidusServerService() {
        return defaultSordidusServerService;
    }

    /**
     * Latiro server service latiro server service.
     *
     * @param headers el headers
     * @return el latiro server service
     */
    @Override
    public LatiroServerService latiroServerService(Map<String, String> headers) {
        return new SessionBasedLatiroServerService(
            latiroServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    /**
     * Latiro server service latiro server service.
     *
     * @return el latiro server service
     */
    @Override
    public LatiroServerService latiroServerService() {
        return defaultLatiroServerService;
    }

    /**
     * Stris server service stris server service.
     *
     * @param headers el headers
     * @return el stris server service
     */
    @Override
    public StrisServerService strisServerService(Map<String, String> headers) {
        return new SessionBasedStrisServerService(
            strisServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    /**
     * Stris server service stris server service.
     *
     * @return el stris server service
     */
    @Override
    public StrisServerService strisServerService() {
        return defaultStrisServerService;
    }

}
