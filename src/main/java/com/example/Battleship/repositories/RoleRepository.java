package com.example.Battleship.repositories;

import com.example.Battleship.entities.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    @Query(value = "select m from RoleEntity m where m.name like :#{#txt}")
    RoleEntity findByName(@Param("txt") String txt);
}
