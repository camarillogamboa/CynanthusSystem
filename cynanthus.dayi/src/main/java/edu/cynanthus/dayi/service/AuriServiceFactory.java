package edu.cynanthus.dayi.service;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuriServiceFactory {

    private final HostAddress hostAddress;
    private final User user;

    @Autowired
    public AuriServiceFactory(
        @Value("${cynanthus.auri.server.hostName}") String hostName,
        @Value("${cynanthus.auri.server.hostPort}") Integer port,
        @Value("${cynanthus.dayi.username}") String username,
        @Value("${cynanthus.dayi.password}") String password
    ) {
        this.hostAddress = HostAddress.create(hostName, port);
        this.user = new User(username, password);
    }

    @Bean
    public AuriSession auriSession() {
        return AuriSession.createRenewableSession(hostAddress, user);
    }

}
