package com.exam.in.config;

import com.exam.in.service.impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private  JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//  1.get token
        String requestTokenheader=request.getHeader("Authorization");  // Authorization is the key which use in postman ,useing this we can get the  (authenticate  token) now we get the data(means authorization hogaya he abb cheack karo)
        //Bearer 23r3443hkjfgk
        System.out.println(requestTokenheader);
        String username=null;
        String token=null;
        if(requestTokenheader!=null&& requestTokenheader.startsWith("Bearer")) {
            token = requestTokenheader.substring(7);
            try {

               // give token to jwtHelper get username
                username = this.jwtHelper.extractUsername(token);
            }catch(IllegalArgumentException e){
                System.out.println("Unable to get jwt token");
            }
            catch(ExpiredJwtException e){
                System.out.println("jwt token has expired");
            }
            catch(MalformedJwtException e){
                System.out.println("invalid jwt");
            }


        }else{
            System.out.println("jwt token does not begain with Bearer");
        }
        //once we get the token , now validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){   //securityContextHolder is where spring security store the details of who is authrnticated.
            UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(username);    //
            if(this.jwtHelper.validateToken(token,userDetails)){
                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("invalid jwt token");
            }

        }else {
            System.out.println("username is null or context is not null ");
        }
        filterChain.doFilter(request,response);











    }
    }

