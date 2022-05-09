package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

import java.net.http.HttpClient;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Supplier;

class RenewableAuriSession implements AuriSession {

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

        Supplier<Map<String, String>> headersSupplier = this::prepareDeafultHeaders;

        this.defaultServerInfoService = new SessionBasedServerInfoService(serverInfoServiceConsumer, sessionManager, headersSupplier);
        this.defaultNodeInfoService = new SessionBasedNodeInfoService(nodeInfoServiceConsumer, sessionManager, headersSupplier);
        this.defaultInstructionSetService = new SessionBasedInstructionSetService(instructionSetServiceConsumer, sessionManager, headersSupplier);
        this.defaultUserService = new SessionBasedUserService(userServiceConsumer, sessionManager, headersSupplier);
        this.defaultSordidusServerService = new SessionBasedSordidusServerService(sordidusServerServiceConsumer, sessionManager, headersSupplier);
        this.defaultLatiroServerService = new SessionBasedLatiroServerService(latiroServerServiceConsumer, sessionManager, headersSupplier);
        this.defaultStrisServerService = new SessionBasedStrisServerService(strisServerServiceConsumer, sessionManager, headersSupplier);
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
        return new SessionBasedServerInfoService(serverInfoServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public ServerInfoService serverInfoService() {
        return defaultServerInfoService;
    }

    @Override
    public NodeInfoService nodeInfoService(Map<String, String> headers) {
        return new SessionBasedNodeInfoService(nodeInfoServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public NodeInfoService nodeInfoService() {
        return defaultNodeInfoService;
    }

    @Override
    public InstructionSetService instructionSetService(Map<String, String> headers) {
        return new SessionBasedInstructionSetService(instructionSetServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public InstructionSetService instructionSetService() {
        return defaultInstructionSetService;
    }

    @Override
    public UserService userService(Map<String, String> headers) {
        return new SessionBasedUserService(userServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public UserService userService() {
        return defaultUserService;
    }

    @Override
    public SordidusServerService sordidusServerService(Map<String, String> headers) {
        return new SessionBasedSordidusServerService(sordidusServerServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public SordidusServerService sordidusServerService() {
        return defaultSordidusServerService;
    }

    @Override
    public LatiroServerService latiroServerService(Map<String, String> headers) {
        return new SessionBasedLatiroServerService(latiroServerServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public LatiroServerService latiroServerService() {
        return defaultLatiroServerService;
    }

    @Override
    public StrisServerService strisServerService(Map<String, String> headers) {
        return new SessionBasedStrisServerService(strisServerServiceConsumer, sessionManager, () -> prepareHeaders(headers));
    }

    @Override
    public StrisServerService strisServerService() {
        return defaultStrisServerService;
    }

}
