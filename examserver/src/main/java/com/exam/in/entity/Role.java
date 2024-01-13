package com.exam.in.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Role {
    @Id
    private Long roleId;
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
    private Set<UserRole> userRole=new HashSet<UserRole>();

}
