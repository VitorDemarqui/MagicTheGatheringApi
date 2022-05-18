package com.zappts.magic_the_gathering_API.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "list_collection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_carta", nullable = false)
    private Carta carta;

    @Column(name = "qtd_carta", nullable = false)
    private Integer qtdCarta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_list", nullable = false)
    private Lista list;
}