package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.persistence.enumeration.Etat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Commande implements Serializable {
    //  -------------------salma-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id ;
    private String client;
    private String reponsableclient;
    private String adresseclient;
    private String devise;
    @Temporal(TemporalType.DATE)
    private Date datefacture;
    private Double prixht;
    private Double totalremise;
    private Double totaltva;
    private Double totalttc;

    private Boolean archive;

    @Enumerated(EnumType.STRING)
    private Etat etat;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
    private List<ItemCommande> items;

    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Facture facture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
    private List<Reclamation> reclamations;

}
