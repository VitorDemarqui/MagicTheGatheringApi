package com.zappts.magic_the_gathering_API.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "carta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "edition", nullable = false, length = 50)
    private String edition;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_idioma", nullable = false)
    private Idioma idioma;

    @Column(name = "foil", nullable = false)
    private Boolean foil = false;

    @Column(name = "value", nullable = false)
    private Double value;
}