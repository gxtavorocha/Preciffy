package com.Preciffy.precifyapp.Dto;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.PrecificacaoEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PrecificacaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoEntity;

    @OneToOne
    @JoinColumn(name = "custos_id", nullable = false)
    private CustosAdicionaisFixosEntity custosAdicionaisFixosEntity;

    @OneToMany(mappedBy = "precificacao", cascade = CascadeType.ALL, orphanRemoval = true)
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


    public PrecificacaoDTO(PrecificacaoEntity precificacao){
        BeanUtils.copyProperties(precificacao, this);
    }


    public PrecificacaoDTO(){

    }


}
