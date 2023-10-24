package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.CategoriaDTO;
import com.ecommerce.ecommerce.model.CategoriaEntity;
import com.ecommerce.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping(value = "/categoria/listar")
    public List<CategoriaEntity> listar() {
        return categoriaRepository.findAll();
    }

    @GetMapping(value = "/categoria/load/{id}")
    public ResponseEntity load(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaRepository.findById(id));
    }

    @PostMapping(value = "/categoria/salvar")
    public ResponseEntity salvar(@RequestBody CategoriaDTO data) {

        try {
            CategoriaEntity _categoria = new CategoriaEntity(data.categoria());
            this.categoriaRepository.save(_categoria);
            return new ResponseEntity<>(_categoria, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(value = "/categoria/editar/{id}")
    public ResponseEntity editar(@RequestBody CategoriaEntity dados) {
        this.categoriaRepository.save(dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/categoria/excluir/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        this.categoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
