package com.natanmaia.veterinaria.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Raca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_especie")
    private Especie especie;

    @OneToMany(mappedBy = "raca")
    private List<Animal> animais;
}
