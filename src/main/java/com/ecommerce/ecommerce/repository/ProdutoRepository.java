package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
