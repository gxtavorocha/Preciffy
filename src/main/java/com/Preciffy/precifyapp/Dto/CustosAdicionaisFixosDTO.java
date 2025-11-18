package com.Preciffy.precifyapp.Dto;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
@Getter
@Setter
public class CustosAdicionaisFixosDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id",nullable = false)
    private ProdutoEntity produtoEntity;

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


 public CustosAdicionaisFixosDTO(CustosAdicionaisFixosEntity custosAdicionaisFixos){
     BeanUtils.copyProperties(custosAdicionaisFixos,this);
 }
    public CustosAdicionaisFixosDTO(){

    }
}


