package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.server.repository.RoleRepository;
import edu.cynanthus.auri.server.repository.UserRepository;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * El tipo Transactional user service.
 */
@Service
@Qualifier("transactionalUserService")
public class TransactionalUserService extends TransactionalBeanService<User> implements UserService {

    /**
     * Instancia un nuevo Transactional user service.
     *
     * @param userRepository el user repository
     * @param roleRepository el role repository
     */
    @Autowired
    public TransactionalUserService(UserRepository userRepository, RoleRepository roleRepository) {
        super(new BasicUserService(userRepository, roleRepository));
    }

}
