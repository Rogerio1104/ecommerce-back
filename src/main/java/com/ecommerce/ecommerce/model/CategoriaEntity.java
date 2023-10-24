package com.ecommerce.ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Table(name = "categoria")
@Entity(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CATEGORIA")
    private String categoria;

    public CategoriaEntity(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}

