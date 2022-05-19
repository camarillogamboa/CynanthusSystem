package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.User;

import java.util.function.Consumer;

class SessionBasedUserService extends SessionBasedBeanService<User, UserService> implements UserService {

    SessionBasedUserService(
        AuriServiceConsumer<UserService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
