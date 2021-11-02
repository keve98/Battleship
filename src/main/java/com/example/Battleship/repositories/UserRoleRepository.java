package com.example.Battleship.repositories;

import com.example.Battleship.entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Query(value = "select u.roleid from UserRole u where u.userid = :num")
    public String getRoleIdFromUserId(@Param("num") Long id);

}
