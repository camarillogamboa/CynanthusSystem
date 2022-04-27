package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    List<RoleEntity> findAllByIdUser(Integer idUser);

    void deleteAllByIdUser(Integer idUser);

}