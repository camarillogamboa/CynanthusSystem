package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuthService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

class AuthServiceConsumer extends ServiceConsumer implements AuthService {

    AuthServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest);
    }

    @Override
    public AuthenticatedUser auth(User user) {
        checkUser(user);
        return sendAndConsume(
            lazyRequest -> lazyRequest.POST(
                AUTH_SERVICE_PATH + "/login",
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
