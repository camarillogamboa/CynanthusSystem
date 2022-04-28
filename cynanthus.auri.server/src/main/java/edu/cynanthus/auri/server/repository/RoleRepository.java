package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * La interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    /**
     * Find all by id user list.
     *
     * @param idUser el id user
     * @return el list
     */
    List<RoleEntity> findAllByIdUser(Integer idUser);

    /**
     * Delete all by id user.
     *
     * @param idUser el id user
     */
    void deleteAllByIdUser(Integer idUser);

}