package ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.RegisterDTO;
import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/")
    public void get(){

    }

    @PostMapping (value = "/usuario/salvar")
    public ResponseEntity salvar(@RequestBody RegisterDTO data){
        if (this.usuarioRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity new_user = new UsuarioEntity(data.nome(), data.email(),encryptedPassword,data.role());

        this.usuarioRepository.save(new_user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
    } 
   
    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> getMethodName(){
        return usuarioRepository.findAll();
    }
   
    @DeleteMapping(path = {"/usuario/{id}"})
    public ResponseEntity <?> deletar(@PathVariable long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(
        @RequestBody UsuarioEntity usuario,
        @PathVariable Long id
    ){
      UsuarioEntity _user = usuarioRepository.findById(id).get();
      _user = usuario;
      usuarioRepository.save(_user);
      return ResponseEntity.ok().build(); 
    }
}
