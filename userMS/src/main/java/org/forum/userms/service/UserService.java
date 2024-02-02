package org.forum.userms.service;


import org.forum.userms.dao.UserDAO;
import org.forum.userms.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> loadAllUsers(){
        return userDAO.findAll();
    }
    
    @Transactional
    public User getUserByUsername(String username){
        Optional<User> user = userDAO.getUserByEmail(username);
        return user.orElse(null);
    }
    
    @Transactional
    public User getUserById(Long id){
        Optional<User> user = userDAO.findById(id);
        return user.orElse(null);
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
        else return null;
    }
    
    @Transactional
    public User changeBanStatus(String username){
        Optional<User> ou = userDAO.getUserByEmail(username);
        if (ou.isPresent()){
            User user = ou.get();
            if (user.getType().equals("banned")){
                if (user.isActive()){
                    user.setType("normal");
                }
                else {
                    user.setType("unverified");
                }
            }
            else {
                user.setType("banned");
            }
            userDAO.save(user);
            return user;
        }
        else return null;
    }

    @Transactional
    public User changeAdminStatus(String username){
        Optional<User> ou = userDAO.getUserByEmail(username);
        if (ou.isPresent()){
            User user = ou.get();
            if (user.getType().equals("admin")){
                if (user.isActive()){
                    user.setType("normal");
                }
                else {
                    user.setType("unverified");
                }
            }
            else {
                user.setType("admin");
            }
            userDAO.save(user);
            return user;
        }
        else return null;
    }
    
    @Transactional
    public User updateUserEmail(String username, String email){
        Optional<User> check = userDAO.getUserByEmail(email);
        if (check.isPresent()) return null;
        Optional<User> ou = userDAO.getUserByEmail(username);
        if (ou.isPresent()){
            User user = ou.get();
            user.setEmail(email);
            userDAO.save(user);
            return user;
        }
        else return null;
    }
    
    @Transactional
    public List<User> loadUsersByIdList(@NotNull List<Long> list){
        List<User> res = new ArrayList<>();
        for (Long id : list){
            Optional<User> optionalUser = userDAO.findById(id);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                res.add(User.builder()
                        .userId(user.getUserId())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .profileImageURL(user.getProfileImageURL())
                        .build());
            }
            else {
                res.add(null);
            }
        }
        return res;
    }

    @Transactional
    public User updateUserImageURL(Long id, String imageURL) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setProfileImageURL(imageURL);
            userDAO.save(user);
        return user;
        }
        else return null;
    }
}
