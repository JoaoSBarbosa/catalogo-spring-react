package br.com.jbcatalog.jbcatalog.controllers;

import br.com.jbcatalog.jbcatalog.entities.Product;
import br.com.jbcatalog.jbcatalog.entities.dto.CategoryDTO;
import br.com.jbcatalog.jbcatalog.entities.dto.ProductDTO;
import br.com.jbcatalog.jbcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        Page<ProductDTO> productDTOPage = productService.findAll(pageRequest);
        return ResponseEntity.ok().body(productDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok().body(productDTO);
    }
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        dto = productService.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateEntity(
            @RequestBody ProductDTO productDTO,
            @PathVariable Long id
    ){
        productDTO = productService.updateRegister(productDTO, id);
        return ResponseEntity.ok().body(productDTO);
    }




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ProductDTO dto = productService.delete(id);
    }
}
