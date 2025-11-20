package com.Preciffy.precifyapp.Dto.Request;

import java.math.BigDecimal;

public record CustosManuaisRequestDTO(
        String nome,
        String categoria,
        BigDecimal custo
){
}
