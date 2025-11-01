package com.Preciffy.precifyapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custos")
public class CustosAdicionais extends Produto{

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
