package com.Preciffy.precifyapp.entity;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false,unique = true)
    private String categoria;

    @Column(nullable = false,precision = 12,scale = 4)
    private BigDecimal precoDeCusto;


   @Column(nullable = false,precision = 5,scale = 4)
   private BigDecimal margemDesejada;



}
