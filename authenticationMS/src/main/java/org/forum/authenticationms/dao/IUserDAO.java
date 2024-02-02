package org.forum.authenticationms.dao;

import org.forum.authenticationms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
public interface IUserDAO extends JpaRepository<User, Long>{

    public Optional<User> getUserByEmail(String email);


}
