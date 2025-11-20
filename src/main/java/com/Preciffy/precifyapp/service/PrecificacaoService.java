package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.Dto.Request.CustosManuaisRequestDTO;
import com.Preciffy.precifyapp.Dto.Request.PrecificacaoRequestDTO;
import com.Preciffy.precifyapp.Dto.Response.PrecificacaoResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class PrecificacaoService {

    public PrecificacaoResponseDTO calcular(PrecificacaoRequestDTO request) {

        BigDecimal precoCusto = request.produto().precoDeCusto();

        BigDecimal totalCustosFixos = request.custosFixos().valoresDosCustos()
                .values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCustosManuais = request.custosManuais()
                .stream()
                .map(CustosManuaisRequestDTO::custo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal custoTotal = precoCusto
                .add(totalCustosFixos)
                .add(totalCustosManuais)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal margemPercent = request.margemDesejada();          // ex: 30  (30%)
        BigDecimal margemDecimal = margemPercent
                .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP); // ex: 0.30

        BigDecimal precoSugerido = custoTotal.divide(
                BigDecimal.ONE.subtract(margemDecimal),
                2,
                RoundingMode.HALF_UP
        );

        BigDecimal lucroObtido = precoSugerido.subtract(custoTotal).setScale(2, RoundingMode.HALF_UP);

        BigDecimal margemObtida = lucroObtido.divide(precoSugerido, 6, RoundingMode.HALF_UP); // decimal (0.30)

        return new PrecificacaoResponseDTO(
                format(precoCusto),
                request.produto().nome(),
                request.produto().categoria(),
                format(precoSugerido),
                format(custoTotal),
                formatarPorcentagemPercentValue(margemPercent),
                format(lucroObtido),
                formatarPorcentagemFromDecimal(margemObtida)
        );
    }

    private String format(BigDecimal valor) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }

    // recebe valor em PERCENTUAL (ex: 30) e devolve "30.00%"
    private String formatarPorcentagemPercentValue(BigDecimal percentValue) {
        return percentValue.setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
    }

    // recebe valor em DECIMAL (ex: 0.30) e devolve "30.00%"
    private String formatarPorcentagemFromDecimal(BigDecimal decimalValue) {
        BigDecimal pct = decimalValue.multiply(BigDecimal.valueOf(100));
        return pct.setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
    }
}
