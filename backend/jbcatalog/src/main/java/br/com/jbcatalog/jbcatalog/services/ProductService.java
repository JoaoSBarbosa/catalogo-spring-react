package br.com.jbcatalog.jbcatalog.services;

import br.com.jbcatalog.jbcatalog.entities.Category;
import br.com.jbcatalog.jbcatalog.entities.Product;
import br.com.jbcatalog.jbcatalog.entities.dto.CategoryDTO;
import br.com.jbcatalog.jbcatalog.entities.dto.ProductDTO;
import br.com.jbcatalog.jbcatalog.repositories.CategoryRepository;
import br.com.jbcatalog.jbcatalog.repositories.ProductRepository;
import br.com.jbcatalog.jbcatalog.services.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product entity = productOptional.orElseThrow(() -> new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + " 😣). Certifique-se de que o ID está correto e tente novamente. 🫡"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(PageRequest pageRequest) {
        // 1. Busca os produtos paginados do repositório
        Page<Product> productPage = productRepository.findAll(pageRequest);

        // 2. Verifica se a página de produtos não está vazia
        if (!productPage.isEmpty()) {
            // 3. Mapeia cada objeto Product para ProductDTO usando o construtor de ProductDTO
            return productPage.map(product -> new ProductDTO(product, product.getCategories()));
        }
        // 4. Retorna null se a página estiver vazia
        return null;
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();

        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity, entity.getCategories());

    }

    @Transactional
    public ProductDTO updateRegister(ProductDTO productDTO, Long id) {
        try {
            Product actualEntity = productRepository.getReferenceById(id);
            copyDtoToEntity(productDTO, actualEntity);
            actualEntity = productRepository.save(actualEntity);
            return new ProductDTO(actualEntity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + "). Certifique-se de que o ID está correto e tente novamente. 😣");
        }
    }

    @Transactional
    public ProductDTO delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return null;
        } else {
            throw new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + "). Certifique-se de que o ID está correto e tente novamente. 😣");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
        entity.setName(dto.getName());

        entity.getCategories().clear();

        // Percorrendo todas as categories que vieram no dto
        for (CategoryDTO categoryDTO : dto.getCategories()) {

            // Instanciando a categoria pelo id de categoria que veio no dto
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());

            // adicionando na lista de categorias da entidade que sera salva a categoria que veio no dto
            entity.getCategories().add(category);
        }

    }

}
