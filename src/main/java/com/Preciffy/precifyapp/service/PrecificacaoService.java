package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import com.Preciffy.precifyapp.repository.CustosAdicionaisFixosRepository;
import com.Preciffy.precifyapp.repository.CustosAdicionaisManuaisRepository;
import com.Preciffy.precifyapp.repository.PrecificacaoRepository;
import com.Preciffy.precifyapp.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service

public class PrecificacaoService{

    public BigDecimal calcularCustoTotal(ProdutoEntity produto) {
        BigDecimal precoCusto = produto.getPrecoDeCusto();
        BigDecimal custoEmbalagens = produto.getCustosAdicionaisFixosEntity();
        BigDecimal custoImpostos = produto.getCustosAdicionaisManuaisEntity();
        BigDecimal frete = produto.getCustosFixos().getFrete();
        BigDecimal outrosCustos = produto.getCustosManuais().getOutrosCustos();

        BigDecimal taxaMaquina = produto.getCustosFixos().getTaxaMaquina().divide(BigDecimal.valueOf(100));
        BigDecimal taxaSite = produto.getCustosFixos().getTaxaSite().divide(BigDecimal.valueOf(100));

        // soma dos custos diretos
        BigDecimal custoBase = precoCusto
                .add(custoEmbalagens)
                .add(custoImpostos)
                .add(frete)
                .add(outrosCustos);

        // aplica as taxas percentuais
        BigDecimal totalComTaxas = custoBase
                .add(custoBase.multiply(taxaMaquina))
                .add(custoBase.multiply(taxaSite));

        return totalComTaxas;
    }


    }

}
