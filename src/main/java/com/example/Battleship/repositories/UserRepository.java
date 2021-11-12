package com.example.Battleship.repositories;


import com.example.Battleship.entities.UserEntity;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

    @Query(value ="select u from UserEntity u order by u.username")
    List<UserEntity> findAll();


    @Query(value = "select m from UserEntity m where m.username like :#{#txt}")
    UserEntity findByName(@Param("txt") String txt);

    @Transactional
    @Modifying
    @Query(value = "update UserEntity m set m.enabled = 1 where m.verificatonCode like :#{#code}")
    void verify(@Param("code") String code);

    @Query(value = "select m from UserEntity m where m.verificatonCode like :#{#code}")
    UserEntity findByCode(@Param("code") String code);



}
