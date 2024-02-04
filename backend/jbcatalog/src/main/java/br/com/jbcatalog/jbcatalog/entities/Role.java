package br.com.jbcatalog.jbcatalog.entities;

import jakarta.persistence.*;

import java.io.Serializable;

//@Entity
//@Table(name = "tb_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String authority;
    private Long id;

    public Role(){}

    public Role(String authority, Long id) {
        this.authority = authority;
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
