package com.Preciffy.precifyapp.repository;

import com.Preciffy.precifyapp.entity.CustosAdicionaisFixosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustosAdicionaisFixosRepository extends JpaRepository<CustosAdicionaisFixosEntity,Long> {
}
