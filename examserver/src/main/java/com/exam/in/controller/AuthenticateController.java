package com.exam.in.controller;

import com.exam.in.config.JwtHelper;
import com.exam.in.entity.JwtRequest;
import com.exam.in.entity.JwtResponse;
import com.exam.in.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request) throws Exception {
        this.authenticate(request.getUsername(),request.getPassword()); //this line call the authenticate method of this class authenticate this ui and pd
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token= this.jwtHelper.generateToken(userDetails);
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private void authenticate(String username,String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);   //authenticationManager is use to authenticate the username and passward

        }catch(BadCredentialsException c){
            System.out.println("invalid details !!");
            throw new Exception("invalid username and password"+ c.getMessage());
        }
    }

}


