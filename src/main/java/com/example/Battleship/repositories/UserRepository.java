package com.example.Battleship.repositories;


import com.example.Battleship.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

    @Query(value ="select u from UserEntity u order by u.Id")
    List<UserEntity> findAll();


    @Query(value = "select u from UserEntity u where u.Id = id")
    UserEntity findById();

}
