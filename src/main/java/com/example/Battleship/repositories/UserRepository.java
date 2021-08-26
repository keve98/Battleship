package com.example.Battleship.repositories;


import com.example.Battleship.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
//@Primary
public interface UserRepository extends CrudRepository<UserEntity, Long>{

    @Query(value ="select u from UserEntity u order by u.username")
    List<UserEntity> findAll();


    @Query(value = "select m from UserEntity m where m.username like :#{#txt}")
    UserEntity findByName(@Param("txt") String txt);

}
