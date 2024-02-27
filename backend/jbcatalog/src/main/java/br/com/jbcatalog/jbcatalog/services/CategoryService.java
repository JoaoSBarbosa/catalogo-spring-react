package br.com.jbcatalog.jbcatalog.services;

import br.com.jbcatalog.jbcatalog.entities.Category;
import br.com.jbcatalog.jbcatalog.entities.dto.CategoryDTO;
import br.com.jbcatalog.jbcatalog.repositories.CategoryRepository;
import br.com.jbcatalog.jbcatalog.services.exception.ControllerConflictException;
import br.com.jbcatalog.jbcatalog.services.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        Category entity = optionalCategory.orElseThrow(() -> new ControllerNotFoundException("Entidade não encontrada através do id informado: " + id));
        return new CategoryDTO(entity);


        //        return optionalCategory.map(CategoryDTO::new).orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        if (!categoryPage.isEmpty()) {
            return categoryPage.map(CategoryDTO::new);
        }
        return null;
    }

    @Transactional
    public Category delete(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryRepository.getReferenceById(id);
            categoryRepository.deleteById(id);
            return null;
        }else {
            throw new ControllerNotFoundException("Não foi possivel localizar uma categoria com o código informado"+id);
        }

    }

    @Transactional
    public CategoryDTO insert(CategoryDTO entityDto) {
        Category entity = new Category();
        entity.setName(entityDto.getName());
        entity.setCreatedAt(entityDto.getCreatedAt());

        categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
        try {
            Category entity = categoryRepository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setCreatedAt(dto.getCreatedAt());
            entity = categoryRepository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Nenhum registro localizado com o id informado: " + id + " 😶‍🌫️");
        }
    }


}
