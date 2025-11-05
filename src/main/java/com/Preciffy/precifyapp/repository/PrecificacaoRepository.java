package com.Preciffy.precifyapp.repository;


import com.Preciffy.precifyapp.entity.PrecificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecificacaoRepository extends JpaRepository<PrecificacaoEntity,Long> {
}
