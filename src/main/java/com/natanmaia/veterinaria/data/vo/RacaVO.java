package com.natanmaia.veterinaria.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.natanmaia.veterinaria.data.model.Animal;
import com.natanmaia.veterinaria.data.model.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "nome"})
public class RacaVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String nome;

    private Especie especie;

    private List<Animal> animais;

}
