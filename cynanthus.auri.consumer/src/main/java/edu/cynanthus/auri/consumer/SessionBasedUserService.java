package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.domain.User;

import java.util.Map;
import java.util.function.Supplier;

class SessionBasedUserService extends SessionBasedBeanService<User, UserService> implements UserService {

    SessionBasedUserService(
        AuriServiceConsumer<UserService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

}
