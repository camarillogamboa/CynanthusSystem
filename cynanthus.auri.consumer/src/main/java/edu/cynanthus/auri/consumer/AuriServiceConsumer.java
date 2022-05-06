package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.HostAddress;

import java.util.Map;

public interface AuriServiceConsumer<T extends AuriService> {

    T prepare(Map<String, String> headers);

    T prepare(String headerName, String headerValue);

    static AuriServiceConsumer<AuthService> createAuthServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, AuthServiceConsumer::new);
    }

    static AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, ServerInfoServiceConsumer::new);
    }

    static AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, NodeInfoServiceConsumer::new);
    }

    static AuriServiceConsumer<InstructionSetService> createInstructionSetServiceConsumer(
        HostAddress hostAddress
    ) {
        return new AuriServiceConsumerImpl<>(hostAddress, InstructionSetServiceConsumer::new);
    }

    static AuriServiceConsumer<UserService> createUserServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, UserServiceConsumer::new);
    }

    static AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, SordidusServerServiceConsumer::new);
    }

    static AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, LatiroServerServiceConsumer::new);
    }

    static AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(HostAddress hostAddress) {
        return new AuriServiceConsumerImpl<>(hostAddress, StrisServerServiceConsumer::new);
    }

}
