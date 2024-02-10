package br.com.jbcatalog.jbcatalog.services;

import br.com.jbcatalog.jbcatalog.entities.Product;
import br.com.jbcatalog.jbcatalog.entities.dto.ProductDTO;
import br.com.jbcatalog.jbcatalog.repositories.ProductRepository;
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
    public Page<ProductDTO> findAll(PageRequest pageRequest){
        // 1. Busca os produtos paginados do repositório
        Page<Product> productPage = productRepository.findAll(pageRequest);

        // 2. Verifica se a página de produtos não está vazia
        if(!productPage.isEmpty()){
            // 3. Mapeia cada objeto Product para ProductDTO usando o construtor de ProductDTO
            return productPage.map(ProductDTO::new);
        }
        // 4. Retorna null se a página estiver vazia
        return null;
    }




}
