package com.panshi.springbootshirojpa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Permission
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:52
 * @Version
 */
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 4950747272213133772L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String permission;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", role=" + role +
                '}';
    }
}
