package com.Preciffy.precifyapp.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
@Service
public class FormatadorService {

    public FormatadorService() {
    }

    //formata a moeda em reais EX(recebe 5000, devolve R$ 5.000,00)
    protected String formatadorDeMoeda(BigDecimal valor) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }

    // recebe valor em PERCENTUAL (ex: 30) e devolve "30.00%"
    protected String formatarPorcentagem(BigDecimal percentValue) {
        return percentValue.setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
    }

    // recebe valor em DECIMAL (ex: 0.30) e devolve "30.00%"
    protected String formatarEmDecimal(BigDecimal decimalValue) {
        BigDecimal pct = decimalValue.multiply(BigDecimal.valueOf(100));
        return pct.setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
    }
}



