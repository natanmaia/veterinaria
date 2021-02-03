package com.natanmaia.veterinaria.repository;

import com.natanmaia.veterinaria.data.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
