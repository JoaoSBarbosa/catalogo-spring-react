package br.com.jbcatalog.jbcatalog.repositories;

import br.com.jbcatalog.jbcatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository; // Arrage: Instancia os objetos necessarios

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L; // Arrage: Instancia os objetos necessarios
        repository.deleteById(existingId); // Act: Execute as ações necessárias

        Optional<Product> product = repository.findById(existingId);
        //assertFalse=> Testando se é falso
        //isPresent => Verifique se esta presente
        Assertions.assertFalse(product.isPresent());

    }
}
