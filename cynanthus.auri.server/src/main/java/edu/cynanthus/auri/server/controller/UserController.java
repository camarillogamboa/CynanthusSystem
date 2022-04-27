package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cynanthus/auri/user")
public class UserController extends BeanController<User> implements UserService {

    @Autowired
    public UserController(
        @Qualifier("transactionalUserService") UserService beanService
    ) {
        super(beanService);
    }

    @GetMapping("/{username:" + Patterns.IDENTIFIER + "}")
    @ResponseBody
    public User readByUsername(User bean) {
        return read(bean);
    }

    @DeleteMapping("/{username:" + Patterns.IDENTIFIER + "}")
    @ResponseBody
    public User deleteByUsername(User bean) {
        return delete(bean);
    }

}
