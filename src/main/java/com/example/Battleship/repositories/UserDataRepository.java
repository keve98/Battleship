package com.example.Battleship.repositories;

import com.example.Battleship.entities.UserDataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends CrudRepository<UserDataEntity, Long> {

    @Query(value = "select u from UserDataEntity u where u.username like :#{#txt}")
    UserDataEntity findDataByName(@Param("txt") String txt);

    @Query(value = "select u from UserDataEntity u order by u.username")
    List<UserDataEntity> findAll();

    @Query(value = "select distinct d.username, d.name, d.address, d.phone, d.email from UserDataEntity d, UserEntity e, RoleEntity r\n" +
            "where r.name = 'USER' and d.username = e.username and e.id = r.id")
    List<Object> findUsersOnly();


}
