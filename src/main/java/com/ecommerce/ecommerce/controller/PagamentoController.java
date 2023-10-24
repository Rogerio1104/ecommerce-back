package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.model.PagamentoEntity;
import com.ecommerce.ecommerce.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PagamentoController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @GetMapping(value = "/pagamento/listar")
    public List<PagamentoEntity> getMethodName() {
        return pagamentoRepository.findAll();
    }

    @GetMapping(value = "/pagamento/load/{id}")
    public Optional<PagamentoEntity> get(@PathVariable Long id) {
        Optional<PagamentoEntity> _pagamento = pagamentoRepository.findById(id);
        return _pagamento;
    }

    @PostMapping(value = "/pagamento")
    public ResponseEntity<PagamentoEntity> salvar(@RequestBody PagamentoEntity pagamento) {
        PagamentoEntity _pagamento = pagamentoRepository.save(pagamento);
        return new ResponseEntity(_pagamento, HttpStatus.CREATED);
    }

    @PutMapping(value = "/pagamento/{id}")
    public ResponseEntity<PagamentoEntity> atualizar(@RequestBody PagamentoEntity updated, @PathVariable Long id) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id).get();
        pagamento.setTipoPagamento(updated.getTipoPagamento());

        pagamentoRepository.save(pagamento);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/pagamento/{id}"})
    public ResponseEntity<?> deletar(@PathVariable long id) {
        pagamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
