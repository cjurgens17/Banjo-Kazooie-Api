package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.Jiggy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JiggyRepository extends JpaRepository<Jiggy,Long> {
}
