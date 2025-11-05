package com.Preciffy.precifyapp.repository;

import com.Preciffy.precifyapp.entity.CustosAdicionaisManuaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustosAdicionaisManuaisRepository extends JpaRepository<CustosAdicionaisManuaisEntity,Long> {
}
