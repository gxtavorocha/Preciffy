package com.Preciffy.precifyapp.Dto;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.PrecificacaoEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long SKU;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false,unique = true,name = "categoria")
    private String categoria;

    @Column(nullable = false, unique = true,name = "preco_de_custo")
    private BigDecimal precoDeCusto;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustosAdicionaisFixosEntity custosAdicionaisFixosEntity;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CustosAdicionaisManuaisEntity> custosAdicionaisManuaisEntity = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrecificacaoEntity> historicoPrecificacaoEntity = new ArrayList<>();


    public Long getSKU() {
        return SKU;
    }

    public void setSKU(Long SKU) {
        this.SKU = SKU;
    }

    public String getNomeDoProduto() {
        return nome;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nome = nomeDoProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecoDeCusto() {
        return precoDeCusto;
    }

    public void setPrecoDeCusto(BigDecimal precoDeCusto) {
        this.precoDeCusto = precoDeCusto;
    }


    public ProdutoDTO(ProdutoEntity produto){
        BeanUtils.copyProperties(produto, this);
    }

    public ProdutoDTO(){

    }

}

