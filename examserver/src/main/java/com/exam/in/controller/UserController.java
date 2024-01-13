package com.exam.in.controller;

import com.exam.in.entity.Role;
import com.exam.in.entity.User;
import com.exam.in.entity.UserRole;
import com.exam.in.payload.ApiResponse;
import com.exam.in.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public User CreateUser(@RequestBody User user) throws Exception {

        Set< UserRole> roles =new HashSet<UserRole>();
         Role role=new Role();
        role.setRoleId(45L);
        role.setRoleName("normal");

        UserRole userrole =new UserRole();
        userrole.setUser(user);
        userrole.setRole(role);
        roles.add(userrole);
        return this.userService.CreateUser(user,roles);


    }
    //get user through username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username ){
        User user1=  this. userService.getUser(username);
        return new ResponseEntity<User>(user1, HttpStatus.OK);
    }
    //get user through email
    @GetMapping("/email/{emailp}")
    public ResponseEntity<User>get1User(@PathVariable String emailp){
        User usernew=this.userService.getUser1(emailp);
        return new ResponseEntity<User>(usernew,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{username}")

        public ResponseEntity<ApiResponse>deleteUser(@PathVariable String username){
        this.userService.deleteUserNa(username);
        return new ResponseEntity<ApiResponse>(new ApiResponse("data deleted",true),HttpStatus.OK);
    }
}
