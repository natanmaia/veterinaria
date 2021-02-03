package com.natanmaia.veterinaria.data.dto;

import com.natanmaia.veterinaria.data.model.Animal;
import com.natanmaia.veterinaria.data.model.Raca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Integer idade;

    private Long id_raca;

    public AnimalDTO (Animal animal){
        this.id = animal.getId();
        this.idade = animal.getIdade();
        this.nome = animal.getNome();
        this.id_raca = animal.getRaca().getId();
    }

}
