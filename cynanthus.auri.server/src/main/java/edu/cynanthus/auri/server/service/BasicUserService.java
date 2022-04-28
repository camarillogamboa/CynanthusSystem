package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.auri.api.UserService;
import edu.cynanthus.auri.server.entity.RoleEntity;
import edu.cynanthus.auri.server.entity.UserEntity;
import edu.cynanthus.auri.server.repository.RoleRepository;
import edu.cynanthus.auri.server.repository.UserRepository;
import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.NaturalIdCandidate;
import edu.cynanthus.bean.ValueUpdater;
import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * El tipo Basic user service.
 */
class BasicUserService extends BasicBeanService<Integer, User, UserEntity> implements UserService {

    /**
     * El User jpa.
     */
    private final UserRepository userJpa;
    /**
     * El Role jpa.
     */
    private final RoleRepository roleJpa;

    /**
     * Instancia un nuevo Basic user service.
     *
     * @param userJpa el user jpa
     * @param roleJpa el role jpa
     */
    BasicUserService(UserRepository userJpa, RoleRepository roleJpa) {
        super(userJpa);
        this.userJpa = userJpa;
        this.roleJpa = Objects.requireNonNull(roleJpa);
    }

    /**
     * Create user.
     *
     * @param user el user
     * @return el user
     */
    @Override
    public User create(User user) {
        checkNotNull(user);
        user.setId(null);
        return saveUserInfo(user, userJpa.save((UserEntity) user));
    }

    /**
     * Read user.
     *
     * @param user el user
     * @return el user
     */
    @Override
    public User read(User user) {
        checkNotNull(user);
        User result = safeFind(user);
        findRoles(result);
        return result;
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    public List<? extends User> read() {
        List<? extends User> users = super.read();

        for (User user : users) findRoles(user);

        return users;
    }

    /**
     * Update user.
     *
     * @param user el user
     * @return el user
     */
    @Override
    public User update(User user) {
        checkNotNull(user);

        UserEntity entity = safeFind(user);

        ValueUpdater.updateIfNotNull(user.getUsername(), entity::setUsername);
        ValueUpdater.updateIfNotNull(user.getPassword(), entity::setPassword);

        entity = userJpa.save(entity);

        roleJpa.deleteAllByIdUser(entity.getId());

        return saveUserInfo(user, entity);
    }

    /**
     * Delete user.
     *
     * @param user el user
     * @return el user
     */
    @Override
    public User delete(User user) {
        checkNotNull(user);
        User result = read(user);
        userJpa.deleteById(result.getId());
        return result;
    }

    /**
     * Find roles.
     *
     * @param user el user
     */
    private void findRoles(User user) {
        List<? extends Role> roles = roleJpa.findAllByIdUser(user.getId());
        user.setRoles(roles.stream().map(Role::clone).collect(Collectors.toList()));
    }

    /**
     * Find optional.
     *
     * @param user el user
     * @return el optional
     */
    Optional<UserEntity> find(User user) {
        if (BeanValidation.validate(user, IdCandidate.class).isEmpty())
            return userJpa.findById(user.getId());
        else if (BeanValidation.validate(user, NaturalIdCandidate.class).isEmpty())
            return userJpa.findByUsername(user.getUsername());

        throw new ServiceException(
            "Se requiere un identificador válido del User",
            ExceptionType.INVALID_ID
        );
    }

    /**
     * Safe find user entity.
     *
     * @param bean el bean
     * @return el user entity
     */
    @Override
    UserEntity safeFind(User bean) {
        return find(bean).orElseThrow(() -> new ServiceException(
            "Registro User{" +
                (bean.getId() != null ? bean.getId() : bean.getUsername()) +
                "} no existe", ExceptionType.NO_EXISTING_ELEMENT
        ));
    }

    /**
     * Check not null.
     *
     * @param bean el bean
     */
    @Override
    void checkNotNull(User bean) {
        if (bean == null)
            throw new ServiceException("El elemento User no debe ser nulo", ExceptionType.NULL_POINTER);
    }

    /**
     * Save user info user.
     *
     * @param user   el user
     * @param entity el entity
     * @return el user
     */
    private User saveUserInfo(User user, UserEntity entity) {
        List<Role> roles = new LinkedList<>();

        if (user.getRoles() != null)
            user.getRoles().forEach(role ->
                roles.add(roleJpa.save(new RoleEntity(null, entity.getId(), role.getRoleType())).clone())
            );

        entity.setRoles(roles);
        return entity;
    }

}
