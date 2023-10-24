package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.ProdutoEntity;
import com.ecommerce.ecommerce.model.SubcategoriaEntity;
import com.ecommerce.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping(value = "/produto/listar")
    public List<ProdutoEntity> getMethodName() {
        return produtoRepository.findAll();
    }

    @GetMapping(value = "/produto/load/{id}")
    public Optional<ProdutoEntity> get(@PathVariable Long id) {
        Optional<ProdutoEntity> _produto = produtoRepository.findById(id);
        return _produto;
    }

    @PostMapping(value = "/produto")
    public ResponseEntity<ProdutoEntity> salvar(@RequestBody ProdutoEntity produto) {
        ProdutoEntity _produto = produtoRepository.save(produto);
        return new ResponseEntity(_produto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<ProdutoEntity> atualizar(@RequestBody ProdutoEntity updated, @PathVariable Long id) {
        ProdutoEntity produto = produtoRepository.findById(id).get();
        produto.setNome(updated.getNome());
        produto.setDescricao(updated.getDescricao());
        produto.setPreco(updated.getPreco());
        produto.setCategoria(updated.getCategoria());
        produto.setSubcategoria(updated.getSubcategoria());

        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/produto/{id}"})
    public ResponseEntity<?> deletar(@PathVariable long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
