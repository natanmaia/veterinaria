package com.natanmaia.veterinaria.repository;

import com.natanmaia.veterinaria.data.model.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {
}
