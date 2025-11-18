package com.Preciffy.precifyapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
public class ProdutoEntity  implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long SKU;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false,unique = true,name = "categoria")
    private String categoria;

    @Column(nullable = false, unique = true,name = "preco_de_custo")
    private BigDecimal precoDeCusto;

    @OneToMany(mappedBy = "produtoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrecificacaoEntity> historicoPrecificacaoEntity = new ArrayList<>();


}
