package com.Preciffy.precifyapp.Controller;

import com.Preciffy.precifyapp.Dto.Request.PrecificacaoRequestDTO;
import com.Preciffy.precifyapp.Dto.Response.PrecificacaoResponseDTO;

import com.Preciffy.precifyapp.service.PrecificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    @RestController
    @RequestMapping("/precificacao")
    public class PrecificacaoController {

        private final PrecificacaoService precificacaoService;

        public PrecificacaoController(PrecificacaoService precificacaoService) {
            this.precificacaoService = precificacaoService;
        }

        @PostMapping("/calcular")
        public ResponseEntity<PrecificacaoResponseDTO> calcular(@RequestBody PrecificacaoRequestDTO request) {

            PrecificacaoResponseDTO resultado = precificacaoService.calcular(request);

            // Aqui o Spring converte automaticamente o DTO em JSON
            return ResponseEntity.ok(resultado);
        }
    }



}

