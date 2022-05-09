package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.HostAddress;

import java.net.http.HttpClient;
import java.util.Map;

public interface AuriServiceConsumer<T extends AuriService> {

    T prepare();

    T prepare(Map<String, String> headers);

    T prepare(String headerName, String headerValue);

    static AuriServiceConsumer<AuthService> createAuthServiceConsumer(HttpClient httpClient, HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, AuthServiceConsumer::new);
    }

    static AuriServiceConsumer<AuthService> createAuthServiceConsumer(HostAddress hostAddress) {
        return createAuthServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, ServerInfoServiceConsumer::new);
    }

    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(HostAddress hostAddress) {
        return createServerInfoServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, NodeInfoServiceConsumer::new);
    }

    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(HostAddress hostAddress) {
        return createNodeInfoServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<InstructionSetService> createInstructionSetServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, InstructionSetServiceConsumer::new);
    }

    static AuriServiceConsumer<InstructionSetService> createInstructionSetServiceConsumer(
        HostAddress hostAddress
    ) {
        return createInstructionSetServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<UserService> createUserServiceConsumer(HttpClient httpClient, HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, UserServiceConsumer::new);
    }

    static AuriServiceConsumer<UserService> createUserServiceConsumer(HostAddress hostAddress) {
        return createUserServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, SordidusServerServiceConsumer::new);
    }

    static AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(HostAddress hostAddress) {
        return createSordidusServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, LatiroServerServiceConsumer::new);
    }

    static AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(HostAddress hostAddress) {
        return createLatiroServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

    static AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(
        HttpClient httpClient,
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(httpClient, hostAddress, StrisServerServiceConsumer::new);
    }

    static AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(HostAddress hostAddress) {
        return createStrisServerServiceConsumer(HttpClient.newHttpClient(), hostAddress);
    }

}
