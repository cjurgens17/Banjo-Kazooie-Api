package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.JiggyPad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JiggyPadRepository extends JpaRepository<JiggyPad, Long> {
}
