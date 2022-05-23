package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.User;

import java.lang.reflect.Type;
import java.util.List;

class UserServiceConsumer extends BeanServiceConsumer<User> implements UserService {

    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>() {}.getType();

    UserServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            USER_SERVICE_PATH,
            User.class,
            USER_LIST_TYPE
        );
    }

    @Override
    Object getId(User bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getUsername() != null) return bean.getUsername();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
