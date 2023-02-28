package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factureavoir")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FactureAvoir implements Serializable {
    //  -------------------salma-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id ;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "factureAvoir")
    private List<ItemFactureAvoir> items;

   /* @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "factureAvoir", updatable = false)
    private Devise devisee;

    */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "factureAvoir")
    private List<Paiement> paiements;

}
