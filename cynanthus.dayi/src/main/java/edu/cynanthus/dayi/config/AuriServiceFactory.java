package edu.cynanthus.dayi.config;

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
        this.hostAddress = HostAddress.create(hostName,port);
    }

    @Bean
    public AuriServiceConsumer<ServerInfoService> createServerInfoServiceConsumer(){
        return AuriServiceConsumer.createServerInfoServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<NodeInfoService> createNodeInfoServiceConsumer(){
        return AuriServiceConsumer.createNodeInfoServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<InstructionSetService> createInstructionsetServiceConsumer(){
        return AuriServiceConsumer.createInstructionSetServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<UserService> createUserServiceConsumer(){
        return AuriServiceConsumer.createUserServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<SordidusServerService> createSordidusServerServiceConsumer(){
        return AuriServiceConsumer.createSordidusServerServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<LatiroServerService> createLatiroServerServiceConsumer(){
        return AuriServiceConsumer.createLatiroServerServiceConsumer(hostAddress);
    }

    @Bean
    public AuriServiceConsumer<StrisServerService> createStrisServerServiceConsumer(){
        return AuriServiceConsumer.createStrisServerServiceConsumer(hostAddress);
    }

}
