package com.Preciffy.precifyapp.Dto;

import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.PrecificacaoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Getter
@Setter
public class CustosAdicionaisManuaisDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal valorDoCusto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precificacao_id", nullable = false)
    private PrecificacaoEntity precificacaoEntity;

    public CustosAdicionaisManuaisDTO(CustosAdicionaisManuaisEntity custosAdicionaisManuais){
        BeanUtils.copyProperties(custosAdicionaisManuais,this);
    }

    public CustosAdicionaisManuaisDTO(){

    }

}


