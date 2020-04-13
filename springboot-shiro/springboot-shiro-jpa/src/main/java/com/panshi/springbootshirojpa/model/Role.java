package com.panshi.springbootshirojpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Role
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:51
 * @Version
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 9034577583334342605L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
    private List<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", user=" + user +
                ", permissions=" + permissions +
                '}';
    }
}
