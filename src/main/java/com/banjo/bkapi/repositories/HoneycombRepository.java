package com.banjo.bkapi.repositories;

import com.banjo.bkapi.models.Honeycomb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HoneycombRepository  extends JpaRepository<Honeycomb, Long> {
}
