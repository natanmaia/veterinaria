package com.natanmaia.veterinaria.data.dto;


import com.natanmaia.veterinaria.data.model.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspecieDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public EspecieDTO (Especie especie){
        this.id = especie.getId();
        this.nome = especie.getNome();
    }
}
