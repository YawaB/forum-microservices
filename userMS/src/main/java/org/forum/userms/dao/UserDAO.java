package org.forum.userms.dao;


import org.forum.userms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{
    Optional<User> getUserByEmail(String email);

//    User addUser(User user);
//    List<User> getAllUsers();
//    void add(User user);
//    void update(User user);
}
