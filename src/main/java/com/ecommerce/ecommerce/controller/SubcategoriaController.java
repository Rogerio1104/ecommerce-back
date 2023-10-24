package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.SubcategoriaEntity;
import com.ecommerce.ecommerce.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SubcategoriaController {

    @Autowired
    SubcategoriaRepository subcategoriaRepository;

    @GetMapping(value = "/subcategoria/listar")
    public List<SubcategoriaEntity> getMethodName() {
        return subcategoriaRepository.findAll();
    }

    @GetMapping(value = "/subcategoria/load/{id}")
    public Optional<SubcategoriaEntity> get(@PathVariable Long id) {
        Optional<SubcategoriaEntity> _subcategoria = subcategoriaRepository.findById(id);
        return _subcategoria;
    }

    @PostMapping(value = "/subcategoria")
    public ResponseEntity<SubcategoriaEntity> salvar(@RequestBody SubcategoriaEntity subcategoria) {
        SubcategoriaEntity _subcategoria = subcategoriaRepository.save(subcategoria);
        return new ResponseEntity(_subcategoria, HttpStatus.CREATED);
    }

    @PutMapping(value = "/subcategoria/{id}")
    public ResponseEntity<SubcategoriaEntity> atualizar(@RequestBody SubcategoriaEntity updated, @PathVariable Long id) {
        SubcategoriaEntity subcategoria = subcategoriaRepository.findById(id).get();
        subcategoria.setCategoria(updated.getCategoria());
        subcategoria.setSubcategoria(updated.getSubcategoria());

        subcategoriaRepository.save(subcategoria);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/subcategoria/{id}"})
    public ResponseEntity<?> deletar(@PathVariable long id) {
        subcategoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
