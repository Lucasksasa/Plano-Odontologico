package com.DevMaker.Plano_Odondologico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dentista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cro;

    @Column
    private String especialidade;

    @Column
    private String Telefone;

    @Column
    private String email;


}
