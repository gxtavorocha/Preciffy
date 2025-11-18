package com.Preciffy.precifyapp.service;

import com.Preciffy.precifyapp.entity.ProdutoEntity;
import com.Preciffy.precifyapp.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {


    @Autowired
    private final ProdutoRepository produtoRepository;


    public List<ProdutoEntity> listarTodos(ProdutoEntity produtosEntity){
        return produtoRepository.findAll();
    }


    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("o Produto não existe"));
    }


    public ProdutoEntity salvar(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);
    }

    public void deletarProdutoPorId(Long id) {
        if (!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }


    public ProdutoEntity atualizarPorId(Long id, ProdutoEntity novosDados) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setSKU(novosDados.getSKU());
        produto.setNome(novosDados.getNome());
        produto.setPrecoDeCusto(novosDados.getPrecoDeCusto());
        produto.setCategoria((novosDados.getCategoria()));

        return produtoRepository.save(produto);

    }


}