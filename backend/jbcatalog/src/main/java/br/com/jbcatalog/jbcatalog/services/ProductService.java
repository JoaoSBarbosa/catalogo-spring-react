package br.com.jbcatalog.jbcatalog.services;

import br.com.jbcatalog.jbcatalog.entities.Product;
import br.com.jbcatalog.jbcatalog.entities.dto.ProductDTO;
import br.com.jbcatalog.jbcatalog.repositories.ProductRepository;
import br.com.jbcatalog.jbcatalog.services.exception.ControllerNotFoundException;
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

//    public ProductDTO insert(ProductDTO entity){
//
//      Optional<Product> productOptional = productRepository.findById(entity.getId());
//
//      if(!productOptional.isPresent()){
//          Product product = new Product();
//
//          product.setImgUrl(entity.getImgUrl());
//          product.setPrice(entity.getPrice());
//          product.setName(entity.getName());
//          product.setDate(entity.getDate());
//          product.setDescription(entity.getDescription());
//
//      }
//
//    }

    @Transactional
    public ProductDTO updateRegister(ProductDTO productDTO, Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product actualEntity = productOptional.get();
            actualEntity.setDate(productDTO.getDate());
            actualEntity.setDescription(productDTO.getDescription());
            actualEntity.setName(productDTO.getName());
            actualEntity.setPrice(productDTO.getPrice());
            actualEntity.setImgUrl(productDTO.getImgUrl());

            actualEntity = productRepository.save(actualEntity);

            return new ProductDTO(actualEntity);
        } else {
            throw new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + "). Certifique-se de que o ID está correto e tente novamente. 😣");
        }
    }

    @Transactional
    public ProductDTO delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return null;
        }else {
            throw new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + "). Certifique-se de que o ID está correto e tente novamente. 😣");
        }
    }


}
