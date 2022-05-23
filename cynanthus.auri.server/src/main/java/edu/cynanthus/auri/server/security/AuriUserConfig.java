package edu.cynanthus.auri.server.security;

import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.RoleType;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class AuriUserConfig {

    private final User auriUser;

    public AuriUserConfig(
        @Value("${cynanthus.auri.username}") String username,
        @Value("${cynanthus.auri.password}") String password,
        @Value("${cynanthus.auri.roles}") String roles
    ) {
        List<Role> roleList = new LinkedList<>();

        for (String role : roles.split(",")) roleList.add(new Role(RoleType.valueOf(role)));

        this.auriUser = new User(username, password, roleList);
    }

    @Bean
    public User auriUser() {
        return auriUser;
    }

}
