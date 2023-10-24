package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.ClienteEntity;
import com.ecommerce.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(value = "/cliente/listar")
    public List<ClienteEntity> getMethodName() {
        return clienteRepository.findAll();
    }

    @GetMapping(value = "/cliente/load/{id}")
    public Optional<ClienteEntity> get(@PathVariable Long id) {
        Optional<ClienteEntity> _cliente = clienteRepository.findById(id);
        return _cliente;
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<ClienteEntity> salvar(@RequestBody ClienteEntity cliente) {
        ClienteEntity _cliente = clienteRepository.save(cliente);
        return new ResponseEntity(_cliente, HttpStatus.CREATED);
    }

    @PutMapping(value = "/cliente/{id}")
    public ResponseEntity<ClienteEntity> atualizar(@RequestBody ClienteEntity updated, @PathVariable Long id) {
        ClienteEntity cliente = clienteRepository.findById(id).get();
        cliente.setNome(updated.getNome());
        cliente.setCpf(updated.getCpf());
        cliente.setEmail(updated.getEmail());
        cliente.setCelular(updated.getCelular());
        cliente.setDataNascimento(updated.getDataNascimento());

        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/cliente/{id}"})
    public ResponseEntity<?> deletar(@PathVariable long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
