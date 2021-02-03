package com.natanmaia.veterinaria.data.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String idade;

    @ManyToOne
    @JoinColumn(name = "id_raca")
    private Raca raca;

}
