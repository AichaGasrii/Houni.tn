package com.esprit.achat.persistence.entity;


import com.esprit.achat.persistence.enumeration.StatutLivraison;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePlanification ;

   private String typeLivraison ;
    private LocalDate dateLivraison;

   // private String AdresseClient ;

    private double noteClient =0;
    @Column(name = "Reclamation")
    private Boolean rec ;

    @Enumerated(EnumType.STRING)
    private StatutLivraison statutLivraison;

    @JsonIgnore
    @ManyToOne
    private Livreur livreur;
    @JsonIgnore
    @OneToOne
    private Facture facture;
    @JsonIgnore
    @OneToOne
    private Reclamation reclamation;
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;



    }




