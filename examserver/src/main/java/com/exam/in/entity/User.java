package com.exam.in.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name="USER")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String userName;
    private String password;
    @Column(name="FIRSTNAME")
    private String firstName;
    private String  lastName;
    private String email;
    private String phone;
    private String profile;
@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER ,mappedBy="user")
@JsonIgnore
private Set<UserRole> userRole=new HashSet<>();//------------------------------------------------------------


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set=new HashSet<>();    //means role name (admin)
        this.userRole.forEach((userRole)->{
            set.add(new Authority((userRole.getRole().getRoleName())));      //1//when we do userRole.getRole() we will get the role objects<set> from userrole        2// then we will call  .getRoleName() of Role class
        });                       // upar userRole ka he referance user class me
        return set;
    }                              // Authority means like admin ,normaluser when spring security will need Authrity call this method

    @Override
    public String getUsername() {
        return  userName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserName() {
        return userName;
    }
}
