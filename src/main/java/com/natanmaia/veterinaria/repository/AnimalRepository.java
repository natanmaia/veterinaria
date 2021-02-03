package com.natanmaia.veterinaria.repository;

import com.natanmaia.veterinaria.data.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
