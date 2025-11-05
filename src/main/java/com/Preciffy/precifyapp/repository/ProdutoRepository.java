package com.Preciffy.precifyapp.repository;

import com.Preciffy.precifyapp.entity.ProdutoEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProdutoRepository  extends JpaRepository<ProdutoEntity,Long> {



    Optional<ProdutoEntity> findById(Long id);

    @Transactional
    void deleteById(Long id);
}
