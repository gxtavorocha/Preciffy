package com.Preciffy.precifyapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PrecificacaoRequestDTO {

    private BigDecimal precoDeCusto;
    private BigDecimal taxaDaMaquina;
    private BigDecimal taxaDaPlataformaOnline;
    private BigDecimal embalagemPersonalizada;
    private BigDecimal embalagemDeEnvios;
    private BigDecimal impostosDeImportacao;
    private BigDecimal custosComFretes;

    private List<BigDecimal> custosManuaisInseridos;



}
