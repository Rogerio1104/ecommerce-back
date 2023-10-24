package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.RegisterDTO;
import com.ecommerce.ecommerce.model.UsuarioEntity;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepositorio;

    @GetMapping(value = "/usuario/listar")
    public List<UsuarioEntity> getMethodName() {
        return usuarioRepositorio.findAll();
    }

    @GetMapping(value = "/usuario/load/{id}")
    public Optional<UsuarioEntity> get(@PathVariable Long id){
        Optional<UsuarioEntity> _user = usuarioRepositorio.findById(id);
        return _user;
    }

    @PostMapping(value = "/usuario/salvar")
    public ResponseEntity salvar(@RequestBody RegisterDTO data){
        if (this.usuarioRepositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity new_user = new UsuarioEntity(data.nome(), data.email(),encryptedPassword,data.role());

        usuarioRepositorio.save(new_user);
        return new ResponseEntity(new_user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(@RequestBody UsuarioEntity updated, @PathVariable Long id){
        UsuarioEntity usuario = usuarioRepositorio.findById(id).get();
        usuario.setNome(updated.nome);
        usuario.setEmail(updated.email);
        usuario.setSenha(updated.senha);

        usuarioRepositorio.save(usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/usuario/{id}"})
    public ResponseEntity<?> deletar(@PathVariable long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
