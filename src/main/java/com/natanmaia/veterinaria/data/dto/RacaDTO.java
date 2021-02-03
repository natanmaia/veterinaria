package com.natanmaia.veterinaria.data.dto;

import com.natanmaia.veterinaria.data.model.Especie;
import com.natanmaia.veterinaria.data.model.Raca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RacaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Long id_especie;

    public RacaDTO (Raca raca){
        this.id = raca.getId();
        this.nome = raca.getNome();
        this.id_especie = raca.getEspecie().getId();
    }

}
