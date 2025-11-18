package com.Preciffy.precifyapp.Controller;

import com.Preciffy.precifyapp.entity.ProdutoEntity;
import com.Preciffy.precifyapp.service.ProdutoService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // localhost:8080/produto/1
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> buscarPorId(@PathVariable Long id){
        ProdutoEntity produto = this.produtoService.buscarPorId(id);
        return  ResponseEntity.ok().body(produto);
    }


    @PostMapping
    @Validated(CreatedBy.class)
    public ResponseEntity<Void> criarProduto(@Validated @RequestBody ProdutoEntity produto){
        this.produtoService.salvar(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getSKU()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping
    @Validated(Update.class)
    public ResponseEntity<Void> atualizarProduto(@Validated @RequestBody ProdutoEntity produto, @PathVariable Long id){
        produto.setSKU(id);
        this.produtoService.atualizarPorId(id,produto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

}

