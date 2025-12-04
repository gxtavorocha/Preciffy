package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.Dto.Request.CustosFixosRequestDTO;
import com.Preciffy.precifyapp.Dto.Request.CustosManuaisRequestDTO;
import com.Preciffy.precifyapp.Dto.Request.PrecificacaoRequestDTO;
import com.Preciffy.precifyapp.Dto.Response.PrecificacaoResponseDTO;
import com.Preciffy.precifyapp.Enums.CustosFixos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


@Service
public class PrecificacaoService {

    @Autowired
    FormatadorService formatador;


    public PrecificacaoResponseDTO calcular(PrecificacaoRequestDTO request) {

        BigDecimal precoCusto = request.produto().precoDeCusto();



        Map<String,BigDecimal> mapDeCustosFixos = request.custosFixos().valoresDosCustosFixos();
        String chaveTaxaGateway = CustosFixos.TAXA_DA_PLATAFORMA.name();
        String chaveTaxaMaquina = CustosFixos.TAXA_DA_MAQUINA.name();

        BigDecimal taxaPercentualGateway = mapDeCustosFixos.getOrDefault(chaveTaxaGateway,BigDecimal.ZERO);
        BigDecimal taxaPercentualMaquina = mapDeCustosFixos.getOrDefault(chaveTaxaMaquina,BigDecimal.ZERO);



        BigDecimal taxaDaMaquinaEmDecimal = taxaPercentualMaquina.divide(BigDecimal.valueOf(100),6,RoundingMode.HALF_UP).
                multiply(precoCusto);


        BigDecimal taxaGatewayEmDecimal = taxaPercentualGateway.divide(BigDecimal.valueOf(100),6,RoundingMode.HALF_UP).
                multiply(precoCusto);


        mapDeCustosFixos.put(chaveTaxaMaquina,taxaDaMaquinaEmDecimal);
        mapDeCustosFixos.put(chaveTaxaGateway,taxaGatewayEmDecimal);


        BigDecimal totalCustosFixos = request.custosFixos().
                valoresDosCustosFixos().values()
                .stream().filter(Objects::nonNull).
                reduce(BigDecimal.ZERO ,BigDecimal::add);





        BigDecimal totalCustosManuais = request.custosManuais()
                .stream()
                .map(CustosManuaisRequestDTO::custo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal custoTotal = precoCusto
                .add(totalCustosFixos)
                .add(totalCustosManuais)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal margemPercentualDesejada = request.margemDesejada();// ex: 30  (30%)

        BigDecimal margemDecimalPercentualDesejadaEmDecimal = margemPercentualDesejada
                .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP); // ex: 0.30

        BigDecimal precoSugerido= custoTotal.divide(
                BigDecimal.ONE.subtract(margemDecimalPercentualDesejadaEmDecimal),
                2,
                RoundingMode.HALF_UP
        );


        BigDecimal lucroObtido = precoSugerido.subtract(custoTotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal margemObtida = lucroObtido.divide(precoSugerido, 6, RoundingMode.HALF_UP);



        return new PrecificacaoResponseDTO(
                request.produto().nome(),
                formatador.formatadorDeMoeda(precoCusto),
                request.produto().categoria(),
                formatador.formatarPorcentagem(margemPercentualDesejada),
                formatador.formatadorDeMoeda(custoTotal),
                formatador.formatadorDeMoeda(precoSugerido),
                formatador.formatadorDeMoeda(lucroObtido),
                formatador.formatarEmDecimal(margemObtida)
        );


    }
}
