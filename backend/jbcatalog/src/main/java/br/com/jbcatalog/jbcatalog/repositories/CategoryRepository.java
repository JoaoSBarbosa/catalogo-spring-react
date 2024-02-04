package br.com.jbcatalog.jbcatalog.repositories;

import br.com.jbcatalog.jbcatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
