package br.com.clone.unionmangas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "id_permission")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(name = "description")
    private String description;

    public Permission() { }

    public Permission(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
