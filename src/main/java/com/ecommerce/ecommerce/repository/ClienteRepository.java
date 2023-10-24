package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
