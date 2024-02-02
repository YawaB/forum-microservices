package org.forum.authenticationms.service;


import org.forum.authenticationms.dao.IUserDAO;
import org.forum.authenticationms.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

        private IUserDAO userDAO;

        @Autowired
        public void setUserDAO(IUserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Transactional
        public List<User> loadAllUsers(){
            return userDAO.findAll();
        }

        @Transactional
        public Optional<User> getUserByUsername(String username){
            Optional<User> user = userDAO.getUserByEmail(username);
            return user;
        }

        @Transactional
        public Optional<User> getUserById(long id){
            return userDAO.findById(id);
        }

        @Transactional
        public User addNewUser(User user){
            String username = user.getEmail();
            Optional<User> ou = userDAO.getUserByEmail(username);
            if (ou.isPresent()) return null;
            userDAO.save(user);
            Optional<User> newOU = userDAO.getUserByEmail(username);
            return newOU.orElse(null);
        }

        @Transactional
        public User updateUserType(String username, String type){
            Optional<User> ou = userDAO.getUserByEmail(username);
            if (ou.isPresent()){
                User user = ou.get();
                if (type.equals("normal")){
                    user.setActive(true);
                }
                if (type.equals("unverified")){
                    user.setActive(false);
                }
                user.setType(type);
                userDAO.save(user);
                return user;
            }
            return null;
        }

        @Transactional
        public User updateUser(User user){
            Optional<User> ou = userDAO.getUserByEmail(user.getEmail());
            if (ou.isPresent()){
                User user1 = ou.get();
                user1.setActive(user.isActive());
                user1.setType(user.getType());
                userDAO.save(user1);
                return user1;
            }
            return null;
        }

        @Transactional
        public User deleteUser(String username){
            Optional<User> ou = userDAO.getUserByEmail(username);
            if (ou.isPresent()){
                User user = ou.get();
                userDAO.delete(user);
                return user;
            }
            return null;
        }

        @Transactional
        public User verifyUser(String username){
            Optional<User> ou = userDAO.getUserByEmail(username);
            if (ou.isPresent()){
                User user = ou.get();
                user.setActive(true);
                userDAO.save(user);
                return user;
            }
            return null;
        }

        @Transactional
        public User unverifyUser(String username) {
            Optional<User> ou = userDAO.getUserByEmail(username);
            if (ou.isPresent()) {
                User user = ou.get();
                user.setActive(false);
                userDAO.save(user);
                return user;
            }
            return null;
        }

    public List<User> loadUsersByIdList(List<Long> users) {
        List<User> res = new ArrayList<>();
        for (Long id : users){
            Optional<User> ou = userDAO.findById(id);
            if (ou.isPresent()){
                res.add(ou.get());
            }
        }
        return res;
    }
}
