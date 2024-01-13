package com.exam.in.service.impl;

import com.exam.in.entity.User;
import com.exam.in.entity.UserRole;
import com.exam.in.repo.RoleRepository;
import com.exam.in.repo.UserRepository;
import com.exam.in.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Set;
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;
    @Autowired
    private RoleRepository rolerepo;


    @Override
    public User CreateUser(User user, Set<UserRole> userroles) throws Exception {
        User local= this.userrepo.findByUserName(user.getUserName());
        if(local!=null){// if user data is present method execute
            System.out.println("user is already there!!");
            throw new Exception("user not found ") ;
        }else{
            for(UserRole ur:userroles){
                rolerepo.save(ur.getRole());
            }
            //set<UserRole> property of user class we are adding here
            user.getUserRole().addAll(userroles);
            local=  this.userrepo.save(user);
        }
            return  local;
    }

    @Override
    public User getUser(String username) {
       User user1=  this.userrepo.findByUserName(username);
        return user1;
    }

    @Override
    public User getUser1(String email) {
        User usernew =this.userrepo.findByEmail(email);
        return usernew ;
    }

    @Override
    public void deleteUserNa(String username) {

         User usernew=this.userrepo.findByUserName(username);
         this.userrepo.delete(usernew);
    }
}
