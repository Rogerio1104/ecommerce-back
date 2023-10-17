package ecommerce.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.security.core.userdetails.UserDetails;

import ecommerce.ecommerce.model.UsuarioEntity;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    
    UserDetails findByEmail(String email);
}
