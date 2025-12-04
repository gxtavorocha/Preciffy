package com.Preciffy.precifyapp.Dto.Response;

import java.math.BigDecimal;

public record PrecificacaoResponseDTO(
        String nome,
        String custo,
        String categoria,
        String margemDesejada,
        String custoTotal,
        String precoDeVendaSugerido,
        String lucroObtido,
        String margemObtida

){}
