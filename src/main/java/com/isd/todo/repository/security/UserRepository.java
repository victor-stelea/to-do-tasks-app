package com.isd.todo.repository.security;

import com.isd.todo.domain.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select distinct user from User as user " +
            "inner join fetch user.roles as role " +
            "inner join fetch role.permissions as permission " +
            "where user.username = :username")
    User findByUsername(@Param("username") String username);
}
