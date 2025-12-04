package com.Preciffy.precifyapp.Controller;

import com.Preciffy.precifyapp.Dto.Request.PrecificacaoRequestDTO;
import com.Preciffy.precifyapp.Dto.Response.PrecificacaoResponseDTO;

import com.Preciffy.precifyapp.service.PrecificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    @Autowired
    private final PrecificacaoService precificacaoService;

    @RestController
    @RequestMapping("/precificacao")

    public class PrecificacaoController {
        @PostMapping("/calcular")
        public ResponseEntity<PrecificacaoResponseDTO> calcular(@RequestBody PrecificacaoRequestDTO request) {
            PrecificacaoResponseDTO resultado = precificacaoService.calcular(request);
            return ResponseEntity.ok(resultado);
        }
    }



}

