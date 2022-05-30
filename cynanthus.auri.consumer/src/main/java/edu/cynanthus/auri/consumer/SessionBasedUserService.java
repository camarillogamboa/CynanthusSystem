package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.User;

import java.util.function.Consumer;

/**
 * El tipo Session based user service.
 */
class SessionBasedUserService extends SessionBasedBeanService<User, UserService> implements UserService {

    /**
     * Instancia un nuevo Session based user service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedUserService(
        AuriServiceConsumer<UserService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
