package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * El tipo User controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/user")
public class UserController extends BeanController<User> implements UserService {

    /**
     * Instancia un nuevo User controller.
     *
     * @param beanService el bean service
     */
    @Autowired
    public UserController(
        @Qualifier("transactionalUserService") UserService beanService
    ) {
        super(beanService);
    }

    /**
     * Read by username user.
     *
     * @param bean el bean
     * @return el user
     */
    @GetMapping("/{username:" + Patterns.IDENTIFIER + "}")
    @ResponseBody
    public User readByUsername(User bean) {
        return read(bean);
    }

    /**
     * Delete by username user.
     *
     * @param bean el bean
     * @return el user
     */
    @DeleteMapping("/{username:" + Patterns.IDENTIFIER + "}")
    @ResponseBody
    public User deleteByUsername(User bean) {
        return delete(bean);
    }

}
