package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * La interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Find by username optional.
     *
     * @param username el username
     * @return el optional
     */
    Optional<UserEntity> findByUsername(String username);

}