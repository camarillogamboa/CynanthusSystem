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

class RenewableAuriSession implements AuriSession {

    private Duration timeOut;

    private final Function<Map<String, String>, Consumer<LazyRequest>> requestBuilder;

    private final SessionManager sessionManager;
    private final AuriServiceConsumer<ServerInfoService> serverInfoServiceConsumer;
    private final AuriServiceConsumer<NodeInfoService> nodeInfoServiceConsumer;
    private final AuriServiceConsumer<InstructionSetService> instructionSetServiceConsumer;
    private final AuriServiceConsumer<UserService> userServiceConsumer;
    private final AuriServiceConsumer<SordidusServerService> sordidusServerServiceConsumer;
    private final AuriServiceConsumer<LatiroServerService> latiroServerServiceConsumer;
    private final AuriServiceConsumer<StrisServerService> strisServerServiceConsumer;

    private final ServerInfoService defaultServerInfoService;
    private final NodeInfoService defaultNodeInfoService;
    private final InstructionSetService defaultInstructionSetService;
    private final UserService defaultUserService;
    private final SordidusServerService defaultSordidusServerService;
    private final LatiroServerService defaultLatiroServerService;
    private final StrisServerService defaultStrisServerService;

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

    @Override
    public Duration getTimeOut() {
        return timeOut;
    }

    @Override
    public void setTimeOut(Duration timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return sessionManager.getAuthenticatedUser();
    }

    @Override
    public void login() {
        sessionManager.login();
    }

    @Override
    public void logout() {
        sessionManager.logout();
    }

    private Map<String, String> prepareDeafultHeaders() {
        Map<String, String> headers = new TreeMap<>(String::compareTo);
        String token = "";

        AuthenticatedUser authenticatedUser = sessionManager.getAuthenticatedUser();

        if (authenticatedUser != null && authenticatedUser.getToken() != null)
            token = authenticatedUser.getToken();

        headers.put("Authorization", "Bearer " + token);
        return headers;
    }

    private Map<String, String> prepareHeaders(Map<String, String> externalHeaders) {
        Map<String, String> headers = prepareDeafultHeaders();
        if (externalHeaders != null)
            headers.putAll(externalHeaders);
        return headers;
    }

    @Override
    public ServerInfoService serverInfoService(Map<String, String> headers) {
        return new SessionBasedServerInfoService(
            serverInfoServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    @Override
    public ServerInfoService serverInfoService() {
        return defaultServerInfoService;
    }

    @Override
    public NodeInfoService nodeInfoService(Map<String, String> headers) {
        return new SessionBasedNodeInfoService(nodeInfoServiceConsumer, sessionManager, requestBuilder.apply(headers));
    }

    @Override
    public NodeInfoService nodeInfoService() {
        return defaultNodeInfoService;
    }

    @Override
    public InstructionSetService instructionSetService(Map<String, String> headers) {
        return new SessionBasedInstructionSetService(
            instructionSetServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    @Override
    public InstructionSetService instructionSetService() {
        return defaultInstructionSetService;
    }

    @Override
    public UserService userService(Map<String, String> headers) {
        return new SessionBasedUserService(userServiceConsumer, sessionManager, requestBuilder.apply(headers));
    }

    @Override
    public UserService userService() {
        return defaultUserService;
    }

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

    @Override
    public SordidusServerService sordidusServerService(Map<String, String> headers) {
        return new SessionBasedSordidusServerService(
            sordidusServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    @Override
    public SordidusServerService sordidusServerService() {
        return defaultSordidusServerService;
    }

    @Override
    public LatiroServerService latiroServerService(Map<String, String> headers) {
        return new SessionBasedLatiroServerService(
            latiroServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    @Override
    public LatiroServerService latiroServerService() {
        return defaultLatiroServerService;
    }

    @Override
    public StrisServerService strisServerService(Map<String, String> headers) {
        return new SessionBasedStrisServerService(
            strisServerServiceConsumer,
            sessionManager,
            requestBuilder.apply(headers)
        );
    }

    @Override
    public StrisServerService strisServerService() {
        return defaultStrisServerService;
    }

}
