package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.entity.ProdutoEntity;
import com.Preciffy.precifyapp.repository.CustosAdicionaisManuaisRepository;
import com.Preciffy.precifyapp.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustosAdicionaisManuaisService {
    @Autowired
    private final CustosAdicionaisManuaisRepository custosAdicionaisManuaisRepository;


    public CustosAdicionaisManuaisEntity salvar(CustosAdicionaisManuaisEntity custoManualInserido){
        // 1. Validação do Nome (Não pode ser nulo ou vazio)
        if (custoManualInserido.getNome() == null || custoManualInserido.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do custo não pode ser nulo ou vazio.");
        }

        // 2. Validação do Preço (Não pode ser nulo e deve ser > que 0)
        if (custoManualInserido.getValorDoCusto() == null || custoManualInserido.getValorDoCusto().equals(0)) {
            throw new IllegalArgumentException("O preço do custo  deve ser informado e ser maior que zero.");
        }

        // 3.Caso passe nas validações e gravado no banco o custo criado.
        return custosAdicionaisManuaisRepository.save(custoManualInserido);
    }

        public void deletar(CustosAdicionaisManuaisEntity custosAdicionaisManuais){
            if(custosAdicionaisManuais.getNome().equals(null) || custosAdicionaisManuais.getNome().trim().isEmpty())
                throw new IllegalArgumentException("O nome do custo que voce quer deletar não pode ser vazio ");

            if (custosAdicionaisManuais.getValorDoCusto() == null || custosAdicionaisManuais.getValorDoCusto().equals(0)) {
                throw new IllegalArgumentException("Você não pode excluir um custo que não exista");
        }
           custosAdicionaisManuaisRepository.delete(custosAdicionaisManuais);
}

    public List<CustosAdicionaisManuaisEntity> listarTodosOsCustos(){
        return custosAdicionaisManuaisRepository.findAll();
    }
}