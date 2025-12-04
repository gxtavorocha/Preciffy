package com.Preciffy.precifyapp.Dto.Request;

import java.math.BigDecimal;
import java.util.Map;

public record CustosFixosRequestDTO(
    Map<String,BigDecimal> valoresDosCustosFixos

){}
