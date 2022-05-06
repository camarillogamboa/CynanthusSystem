package edu.cynanthus.dayi.factory;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.auri.consumer.AuriServiceConsumer;
import edu.cynanthus.common.net.HostAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuriServiceFactory {

    private final HostAddress hostAddress;

    @Autowired
    public AuriServiceFactory(
        @Value("${cynanthus.auri.server.hostName}") String hostName,
        @Value("${cynanthus.auri.server.hostPort}") Integer port
    ) {
        this.hostAddress = HostAddress.create(hostName, port);
    }

    @Bean
    public AuriServiceConsumer<AuthService> authServiceConsumer() {
        return AuriServiceConsumer.createAuthServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<ServerInfoService> serverInfoServiceConsumer() {
        return AuriServiceConsumer.createServerInfoServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<NodeInfoService> nodeInfoServiceConsumer() {
        return AuriServiceConsumer.createNodeInfoServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<InstructionSetService> instructionsetServiceConsumer() {
        return AuriServiceConsumer.createInstructionSetServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<UserService> userServiceConsumer() {
        return AuriServiceConsumer.createUserServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<SordidusServerService> sordidusServerServiceConsumer() {
        return AuriServiceConsumer.createSordidusServerServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<LatiroServerService> latiroServerServiceConsumer() {
        return AuriServiceConsumer.createLatiroServerServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<StrisServerService> strisServerServiceConsumer() {
        return AuriServiceConsumer.createStrisServerServiceConsumer(hostAddress);
    }

}
