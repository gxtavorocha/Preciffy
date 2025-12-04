package com.Preciffy.precifyapp.Dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

public record PrecificacaoRequestDTO(

    ProdutoRequestDTO produto,

    CustosFixosRequestDTO custosFixos,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<CustosManuaisRequestDTO> custosManuais,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    BigDecimal margemDesejada
){}
