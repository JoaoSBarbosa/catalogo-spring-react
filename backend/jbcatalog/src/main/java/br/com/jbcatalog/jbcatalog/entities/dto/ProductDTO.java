package br.com.jbcatalog.jbcatalog.entities.dto;

import br.com.jbcatalog.jbcatalog.entities.Category;
import br.com.jbcatalog.jbcatalog.entities.Product;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl,Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;

    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        date = entity.getDate();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        // Chama o construtor da classe ProductDTO que recebe apenas a entidade Product
        this(entity);

        // Para cada objeto de categoria que foi recebido na requisição,
        // cria uma nova instância de CategoryDTO e a adiciona à lista de categorias do ProdutoDTO.
        categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
    }

}
