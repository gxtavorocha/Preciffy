package com.Preciffy.precifyapp.Dto.Request;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        String nome,
        String categoria,
        BigDecimal precoDeCusto

){}
