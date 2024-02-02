package org.forum.authenticationms.controller;


import org.forum.authenticationms.dto.request.NewUserRequest;
import org.forum.authenticationms.dto.request.UserListRequest;
import org.forum.authenticationms.dto.response.AllUsersResponse;
import org.forum.authenticationms.dto.response.UserListResponse;
import org.forum.authenticationms.dto.response.UserResponse;
import org.forum.authenticationms.entities.User;
import org.forum.authenticationms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    public AllUsersResponse getAllUsers(){
        return AllUsersResponse.builder()
                .users(userService.loadAllUsers())
                .build();
    }
    
    @GetMapping("/{id}")
        public UserResponse loadUserById(@PathVariable int id){
            return UserResponse.builder()
                    .message("get user by id successfully")
                    .status("OK")
                .user(userService.getUserById(id)).build();
    }
    

    @GetMapping("/list")
    public UserListResponse loadUsersByIdList(@Valid @RequestBody UserListRequest req){
        List<User> users = userService.loadUsersByIdList(req.getUsers());
        return UserListResponse.builder()
                .message("get user list info successfully")
                .status("OK")
                .users(users).build();
    }
    
    @GetMapping("/info")
    public UserResponse loadUserByUsername(@RequestParam String username){
        System.out.println(username);
        return UserResponse.builder()
                .message("get user by username successfully")
                .status("OK")
                .user(userService.getUserByUsername(username)).build();
    }

    @PostMapping()
    ResponseEntity<UserResponse> addNewUser(@RequestBody NewUserRequest newUserRequest) {
//      TODO implement this method
        User user = User.builder()
                .firstName(newUserRequest.getFirstname())
                .lastName(newUserRequest.getLastname())
                .email(newUserRequest.getEmail())
                .password(newUserRequest.getPassword())
                .active(false)
                .dateJoined(Timestamp.valueOf(LocalDateTime.now()))
                .type("unverified")
                .profileImageURL(newUserRequest.getProfileImageURL())
                .build();
        Optional<User> res = Optional.ofNullable(userService.addNewUser(user));
        if (res == null){
            return ResponseEntity.ok(UserResponse.builder()
                    .message("add user failed")
                    .status("failed")
                    .user(null)
                    .build());
        }
        return ResponseEntity.ok(UserResponse.builder()
                .message("add user successfully")
                .status("OK")
                .user(res)
                .build());
    };

    @PatchMapping("/type")
    ResponseEntity<UserResponse> updateUserByType(@RequestParam String email, @RequestBody String type) {
        Optional<User> user = Optional.ofNullable(userService.updateUserType(email, type));
        return ResponseEntity.ok(UserResponse.builder()
                .message("update user type successfully")
                .status("OK")
                .user(user)
                .build());
    }

}
