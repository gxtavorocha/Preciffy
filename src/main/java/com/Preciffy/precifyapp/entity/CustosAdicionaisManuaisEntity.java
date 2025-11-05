package com.Preciffy.precifyapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "custos_manuais")
public class CustosAdicionaisManuaisEntity  implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal valorDoCusto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precificacao_id", nullable = false)
    private PrecificacaoEntity precificacaoEntity;

}
