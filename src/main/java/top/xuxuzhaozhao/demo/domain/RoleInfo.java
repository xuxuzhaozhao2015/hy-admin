package top.xuxuzhaozhao.demo.domain;

import javax.persistence.*;

@Table(name = "role_info")
public class RoleInfo {
    @Id
    private String id;

    @Column(name = "role_name")
    private String roleName;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}