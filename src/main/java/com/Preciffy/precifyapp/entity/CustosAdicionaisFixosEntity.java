package com.Preciffy.precifyapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custos_adicionais_pre_definidos")
public class CustosAdicionaisFixosEntity implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id",nullable = false)
    private ProdutoEntity produto;

    @Column(nullable = false,precision = 5,scale = 4)
    private BigDecimal taxaDaMaquina;

    @Column(nullable = false,precision = 5,scale = 4)
    private BigDecimal taxaDaPlataformaOnline;

    @Column(nullable = false,precision = 12,scale = 4)
    private BigDecimal embalemPersonalizada;

    @Column(nullable = false,precision = 12,scale = 4)
    private BigDecimal embalagemDeEnvios;

    @Column(nullable = false,precision = 12,scale = 4)
    private BigDecimal impostosDeImportacao;

    @Column(nullable = false,precision = 12,scale = 4)
    private  BigDecimal custosComFretes;

}
