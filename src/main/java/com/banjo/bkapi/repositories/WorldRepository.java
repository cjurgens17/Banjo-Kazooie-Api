package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface WorldRepository extends JpaRepository<World,Long> {

    Optional<World> findByName(String name);
}
