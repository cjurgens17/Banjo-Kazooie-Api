package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.BKCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface BKCharacterRepository extends JpaRepository<BKCharacter, Long> {

    Optional<BKCharacter> findByNameEquals(String name);

}
