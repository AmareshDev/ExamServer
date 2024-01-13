package com.exam.in;

import com.exam.in.entity.Role;
import com.exam.in.entity.User;
import com.exam.in.entity.UserRole;
import com.exam.in.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication  {
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}



//		User user=new User();
//		user.setFirstName("tarun");
//		user.setLastName("panday");
//		user.setUserName("tarun@123");
//		user.setPassword("xyz123");
//		user.setEmail("trun123@gmail.com");
//		user.setPhone("688978937");
//		user.setProfile("default.png");
//
//		Role role=new Role();
//		role.setRoleId(44L);
//		role.setRoleName("Admin");
//
//		Set<UserRole> userRoleSet=new HashSet<>();
//
//
//		UserRole userrole=new UserRole();
//		userrole.setRole(role);
//		userrole.setUser(user);
//		userRoleSet.add(userrole);///add in the set
//
//		User user1=this.userservice.createUser(user, userRoleSet);
//		System.out.println(user1.getUserName());







	}

