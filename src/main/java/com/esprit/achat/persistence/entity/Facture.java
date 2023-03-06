package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "facture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Facture implements Serializable {
    //  -------------------salma-------------------:fournisseur
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id ;
    @NotBlank(message = "ce champ ne doit pas être vide")
    private String client;
    @NotBlank(message = "ce champ ne doit pas être vide")
    private String reponsableclient;
    @NotBlank(message = "ce champ ne doit pas être vide")
    @ValidAdress
    private String adresseclient;
    @NotBlank(message = "Devise est obligatoire")
    private String devise;
    @NotNull(message = "Le champ datefacture ne peut pas être vide")
    @PastOrPresent(message = "La date de la facture doit être dans le passé ou le présent")
    @Temporal(TemporalType.DATE)
    private Date datefacture;
    private LocalDate dateLivraison;
   // @DecimalMin(value = "0.0", inclusive = false, message = "Le total TTC doit être supérieur à 0")
    private Double totalttc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "facture_id")
   private List<ItemFacture> items = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facture")
    private List<Paiement> paiements;


    @ManyToOne
    private Livreur livreur;

    @OneToOne(mappedBy = "facture")
    private Livraison livraison;



}
