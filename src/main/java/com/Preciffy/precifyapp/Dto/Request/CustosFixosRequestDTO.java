package com.Preciffy.precifyapp.Dto.Request;

import com.Preciffy.precifyapp.Enums.CustosFixos;

import java.math.BigDecimal;
import java.util.Map;

public record CustosFixosRequestDTO(
    Map<String, BigDecimal> valoresDosCustos

){}
