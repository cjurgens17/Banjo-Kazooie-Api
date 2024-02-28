package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface WorldRepository extends JpaRepository<World,Long> {
}
