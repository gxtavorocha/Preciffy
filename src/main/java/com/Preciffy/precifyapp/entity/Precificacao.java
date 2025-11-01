package com.Preciffy.precifyapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "precificacoes")
public class Precificacao extends Produto {


    @Id

    private Long id;







    @Column(nullable = false,precision = 12,scale = 4)
    private BigDecimal precoDeVendaSugerido;

    @Column(nullable = false, precision = 12,scale = 4)
    private BigDecimal lucroBruto;



}
