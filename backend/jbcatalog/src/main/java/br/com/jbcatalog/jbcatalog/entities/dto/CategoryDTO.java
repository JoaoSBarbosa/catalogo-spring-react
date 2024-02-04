package br.com.jbcatalog.jbcatalog.entities.dto;


import br.com.jbcatalog.jbcatalog.entities.Category;

import java.io.Serializable;
import java.time.Instant;

public class CategoryDTO extends Category implements Serializable {
    private Long id;

    private String name;

    private Instant createdAt;
    public CategoryDTO(){}

    public CategoryDTO(Long id, String name,Instant createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt =createdAt;
    }

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
        createdAt = entity.getCreatedAt();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
