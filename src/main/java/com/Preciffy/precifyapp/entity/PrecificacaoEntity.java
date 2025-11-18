package com.Preciffy.precifyapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "precificacao")
public class PrecificacaoEntity  implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoEntity;

    @OneToOne
    @JoinColumn(name = "custos_id", nullable = false)
    private CustosAdicionaisFixosEntity custosAdicionaisFixosEntity;

    @OneToMany(mappedBy = "precificacaoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustosAdicionaisManuaisEntity> custosManuais = new ArrayList<>();

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal custoTotalConsolidado;

    @Column(nullable = false, precision = 5, scale = 4)
    private BigDecimal margemDesejada;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal precoDeVendaSugerido;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal lucroBrutoEmReais;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal margemObtidaEmPorcentagem;

    @Column(nullable = false,precision = 12,scale =4)
    private BigDecimal proLaboreEmReaisPorVenda;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataDoCalculo = LocalDateTime.now();







}