package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.server.repository.RoleRepository;
import edu.cynanthus.auri.server.repository.UserRepository;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionalUserService extends TransactionalBeanService<User> implements UserService {

    @Autowired
    public TransactionalUserService(UserRepository userRepository, RoleRepository roleRepository) {
        super(new BasicUserService(userRepository, roleRepository));
    }

}
