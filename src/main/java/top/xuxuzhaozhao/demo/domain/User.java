package top.xuxuzhaozhao.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;

@Table(name = "user_info")
public class User implements Serializable {
    @Id
    private String id;
    @Column(name = "user_name")
    private String username;

    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    private Set<String> perms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }
}
