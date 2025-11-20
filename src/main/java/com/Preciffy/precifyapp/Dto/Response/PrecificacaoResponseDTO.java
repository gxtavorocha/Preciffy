package com.Preciffy.precifyapp.Dto.Response;

import java.math.BigDecimal;

public record PrecificacaoResponseDTO(
        String custo,
        String nome,
        String categoria,
        String precoDeVendaSugerido,
        String custoTotal,
        String margemDesejada,
        String lucroObtido,
        String margemObtida

){}
