package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import com.Preciffy.precifyapp.repository.CustosAdicionaisFixosRepository;
import com.Preciffy.precifyapp.repository.CustosAdicionaisManuaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustosAdicionaisFixosService {

    private final CustosAdicionaisFixosRepository repository;

    //Salva Custos inseridos pelo usuario
    public CustosAdicionaisFixosEntity salvar(CustosAdicionaisFixosEntity custosInseridos) {
        return repository.save(custosInseridos);
    }

    // Retorna todos os custos fixos já cadastrados
    public List<CustosAdicionaisFixosEntity> listarTodos() {
        return repository.findAll();
    }



}
