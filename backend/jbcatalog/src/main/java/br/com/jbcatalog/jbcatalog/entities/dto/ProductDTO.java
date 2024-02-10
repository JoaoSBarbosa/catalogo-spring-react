package br.com.jbcatalog.jbcatalog.entities.dto;

import br.com.jbcatalog.jbcatalog.entities.Product;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO extends Product implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;

    private List<CategoryDTO> categoryDTOList = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Product entity, Set<CategoryDTO> categories) {
        // Chama o construtor da classe ProductDTO que recebe apenas a entidade Product
        this(entity);

        // Para cada objeto de categoria que foi recebido na requisição,
        // cria uma nova instância de CategoryDTO e a adiciona à lista de categorias do ProdutoDTO.
        categories.forEach(category -> categoryDTOList.add(new CategoryDTO(category)));
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Instant getDate() {
        return date;
    }

    @Override
    public void setDate(Instant date) {
        this.date = date;
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

}
