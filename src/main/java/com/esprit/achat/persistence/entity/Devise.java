package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "devise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Devise implements Serializable {
    //  -------------------salma-------------------:user
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol")
    private String symbol;

 /*   @OneToMany(fetch = FetchType.LAZY, mappedBy = "devisee")
    private List<Facture> factures;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "devisee")
    private List<FactureAvoir> factureAvoirs;

  */
}
