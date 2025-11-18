package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.PrecificacaoEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import com.Preciffy.precifyapp.repository.CustosAdicionaisFixosRepository;
import com.Preciffy.precifyapp.repository.PrecificacaoRepository;
import com.Preciffy.precifyapp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PrecificacaoService {

    @Autowired
    private PrecificacaoRepository precificacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CustosAdicionaisFixosRepository custosFixosRepository;


    /**
     * Método para calcular o custo total.
     * Agora ele requer a PrecificacaoEntity para acessar os custos associados.
     */
    public BigDecimal calcularCustoTotal(ProdutoEntity produto, PrecificacaoEntity precificacao) {

        // Custo Base (do Produto)
        BigDecimal precoCusto = produto.getPrecoDeCusto();

        // Custos Fixos Adicionais (agora acessados via PrecificacaoEntity)
        BigDecimal custoEmbalagensPersonalizadas = precificacao.getCustosAdicionaisFixosEntity().getEmbalemPersonalizada();
        BigDecimal custosEmbalagensDeEnvio = precificacao.getCustosAdicionaisFixosEntity().getEmbalagemDeEnvios();
        BigDecimal custoImpostos = precificacao.getCustosAdicionaisFixosEntity().getImpostosDeImportacao();
        BigDecimal frete = precificacao.getCustosAdicionaisFixosEntity().getCustosComFretes();

        // Custos Manuais Adicionais (agora acessados via PrecificacaoEntity)
        List<CustosAdicionaisManuaisEntity> outrosCustos = precificacao.getCustosManuais();

        // Taxas (agora acessadas via PrecificacaoEntity)
        BigDecimal taxaMaquina = precificacao.getCustosAdicionaisFixosEntity().getTaxaDaMaquina()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        BigDecimal taxaSite = precificacao.getCustosAdicionaisFixosEntity().getTaxaDaPlataformaOnline()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        // Soma dos custos manuais
        BigDecimal somaDosOutrosCustos = outrosCustos.stream()
                .map(CustosAdicionaisManuaisEntity::getValorDoCusto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Custo que serve de base para o cálculo das taxas
        BigDecimal custoBase = precoCusto
                .add(custoEmbalagensPersonalizadas)
                .add(custosEmbalagensDeEnvio)
                .add(custoImpostos)
                .add(frete)
                .add(somaDosOutrosCustos);

        // Custo total final (incluindo as taxas)
        BigDecimal totalComTaxas = custoBase
                .add(custoBase.multiply(taxaMaquina))
                .add(custoBase.multiply(taxaSite));

        return totalComTaxas.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Método de calcular preço final usando a margem da precificação.
     * A chamada para calcularCustoTotal foi ajustada para passar 'precificacao'.
     */
    public BigDecimal calcularPrecoFinalDoProduto(ProdutoEntity produto, PrecificacaoEntity precificacao) {

        if (precificacao == null || precificacao.getMargemDesejada() == null) {
            throw new RuntimeException("Margem desejada não definida na precificação.");
        }

        // Converte a margem (%) em decimal
        BigDecimal margem = precificacao.getMargemDesejada()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        // Calcula o custo total do produto (chamada corrigida)
        BigDecimal custoTotal = calcularCustoTotal(produto, precificacao);

        // Fórmula: Preço Final = custoTotal / (1 - margem)
        BigDecimal divisor = BigDecimal.ONE.subtract(margem);

        return custoTotal.divide(divisor, 2, RoundingMode.HALF_UP);
    }
}