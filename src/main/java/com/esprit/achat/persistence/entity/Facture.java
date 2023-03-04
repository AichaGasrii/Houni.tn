package com.esprit.achat.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Facture implements Serializable {
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
    private LocalDate dateLivraison;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facture")
   private List<ItemFacture> items;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facture")
    private List<Paiement> paiements;



    @ManyToOne
    private Livreur livreur;

    @OneToOne(mappedBy = "facture")
    private Livraison livraison;


}
