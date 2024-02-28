package com.banjo.bkapi.repositories;


import com.banjo.bkapi.enums.Color;
import com.banjo.bkapi.models.Jinzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface JinzoRepository extends JpaRepository<Jinzo, Long> {

    Optional<Jinzo> findByColor(Color color);
}
