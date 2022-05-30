package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.net.http.HttpClient;
import java.util.function.Consumer;

/**
 * La interface Auri service consumer.
 *
 * @param <T> el parámetro de tipo
 */
public interface AuriServiceConsumer<T extends AuriService> {

    /**
     * Prepare t.
     *
     * @return el t
     */
    T prepare();

    /**
     * Prepare t.
     *
     * @param lazyRequestConsumer el lazy request consumer
     * @return el t
     */
    T prepare(Consumer<LazyRequest> lazyRequestConsumer);

    /**
     * Create auth service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<AuthService> createAuthServiceConsumer(HttpClient httpClient, HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, AuthServiceConsumer::new);
    }

    /**
     * Create auth service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<AuthService> createAuthServiceConsumer(HostAddress hostAddress) {
        return createAuthServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create server info service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, ServerInfoServiceConsumer::new);
    }

    /**
     * Create server info service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(HostAddress hostAddress) {
        return createServerInfoServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create node info service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, NodeInfoServiceConsumer::new);
    }

    /**
     * Create node info service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(HostAddress hostAddress) {
        return createNodeInfoServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create instruction set service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<InstructionSetService> createInstructionSetServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, InstructionSetServiceConsumer::new);
    }

    /**
     * Create instruction set service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<InstructionSetService> createInstructionSetServiceConsumer(
        HostAddress hostAddress
    ) {
        return createInstructionSetServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create user service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<UserService> createUserServiceConsumer(HttpClient httpClient, HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, UserServiceConsumer::new);
    }

    /**
     * Create user service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<UserService> createUserServiceConsumer(HostAddress hostAddress) {
        return createUserServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create sordidus server service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, SordidusServerServiceConsumer::new);
    }

    /**
     * Create sordidus server service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(HostAddress hostAddress) {
        return createSordidusServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create latiro server service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, LatiroServerServiceConsumer::new);
    }

    /**
     * Create latiro server service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(HostAddress hostAddress) {
        return createLatiroServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    /**
     * Create stris server service consumer auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, StrisServerServiceConsumer::new);
    }

    /**
     * Create stris server service consumer auri service consumer.
     *
     * @param hostAddress el host address
     * @return el auri service consumer
     */
    static AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(HostAddress hostAddress) {
        return createStrisServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

}
