package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.User;

import java.util.List;

class UserServiceClient extends BeanServiceClient<User> implements UserService {

    UserServiceClient(ClientInfo clientInfo) {
        super(
            clientInfo,
            "/cynanthus/auri/user",
            User.class,
            new TypeToken<List<User>>() {}.getType()
        );
    }

    @Override
    Object getId(User bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getUsername() != null) return bean.getUsername();
        else throw new ServiceException(
                "Se requiere un identificador válido para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );
    }

}
