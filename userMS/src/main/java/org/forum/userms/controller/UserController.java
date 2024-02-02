package org.forum.userms.controller;


import org.forum.userms.dto.request.NewUserRequest;
import org.forum.userms.dto.request.UserListRequest;
import org.forum.userms.dto.response.AllUsersResponse;
import org.forum.userms.dto.response.UserListResponse;
import org.forum.userms.dto.response.UserResponse;
import org.forum.userms.entities.User;
import org.forum.userms.security.AuthUserDetail;
import org.forum.userms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public AllUsersResponse getAllUsers(){
        return AllUsersResponse.builder()
                .users(userService.loadAllUsers())
                .build();
    }
    
    @GetMapping("/{id}")
    public UserResponse loadUserById(@PathVariable Long id){
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

    @PatchMapping("/active")
    ResponseEntity<UserResponse> changeBanStatus(@RequestBody String username) {
//      TODO implement this method
        return ResponseEntity.ok(UserResponse.builder()
                .message("change ban status successfully")
                .status("OK")
                .user(userService.changeBanStatus(username))
                .build());
    };

    @PatchMapping("/promote")
    ResponseEntity<UserResponse> changeAdminStatus(@RequestBody String username) {
        return ResponseEntity.ok(UserResponse.builder()
                .message("change admin status successfully")
                .status("OK")
                .user(userService.changeAdminStatus(username))
                .build());
    };

    @PostMapping()
    ResponseEntity<UserResponse> addNewUser(@RequestBody NewUserRequest newUserRequest) {
//      TODO implement this method
        User user = User.builder()
                .firstname(newUserRequest.getFirstname())
                .lastname(newUserRequest.getLastname())
                .email(newUserRequest.getEmail())
                .password(newUserRequest.getPassword())
                .active(false)
                .dateJoined(LocalDateTime.now())
                .type("unverified")
                .profileImageURL(newUserRequest.getProfileImageURL())
                .build();
        User res = userService.addNewUser(user);
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
        User user = userService.updateUserType(email, type);
        return ResponseEntity.ok(UserResponse.builder()
                .message("update user type successfully")
                .status("OK")
                .user(user)
                .build());
    }
    
    @PatchMapping("/email")
    ResponseEntity<UserResponse> updateUserByEmail(@RequestBody String email,
                                                  @AuthenticationPrincipal AuthUserDetail authUserDetail) {
//      TODO implement this method
        String username = authUserDetail.getUsername();
        User user = userService.updateUserEmail(username, email);
        if (user == null){
            return ResponseEntity.ok(UserResponse.builder()
                    .message("update user email failed")
                    .status("failed")
                    .user(null)
                    .build());
        }
        return ResponseEntity.ok(UserResponse.builder()
                .message("update user email successfully")
                .status("OK")
                .user(user)
                .build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<UserResponse> updateUserById(@PathVariable Long id,
                                                  @RequestBody String ImageURL) {
        User user = userService.updateUserImageURL(id, ImageURL);
        return ResponseEntity.ok(UserResponse.builder()
                .message("update user type successfully")
                .status("OK")
                .user(user)
                .build());
    }
}
