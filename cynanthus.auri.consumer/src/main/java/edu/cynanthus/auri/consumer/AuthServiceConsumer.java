package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

class AuthServiceConsumer extends GeneralConsumer implements AuthService {

    AuthServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest);
    }

    @Override
    public AuthenticatedUser auth(User user) {
        checkUser(user);
        return consume(
            lazyRequest -> lazyRequest.POST(
                "/cynanthus/auth/login",
                () -> JsonProvider.toJsonInputStream(user)
            ),
            AuthenticatedUser.class
        );
    }

    private void checkUser(User user) {
        if (user == null)
            throw new InvalidArgumentException("Se requiere un objeto User para realizar esta acción");
    }

}
