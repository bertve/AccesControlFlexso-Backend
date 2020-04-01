package com.springBoot.keyAPI.model;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName roleName;

    private String description;

    public Role() {
    }

    public Role(RoleName role) {
        this.roleName = role;
    }

    public Role(RoleName role,String description) {
        this(role);
        this.description = description;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long id) {
        this.roleId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }


}