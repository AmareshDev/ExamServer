package com.exam.in.service;

import com.exam.in.entity.User;
import com.exam.in.entity.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
@Component
public interface UserService {
    public User CreateUser(User user, Set<UserRole> userroles) throws Exception;

      public User  getUser(String username);

    User getUser1(String email);

    void deleteUserNa(String username);
}
