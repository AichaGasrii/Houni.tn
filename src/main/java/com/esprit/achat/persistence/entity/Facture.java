package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.dto.ValidAdress;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
    private LocalDate datefacture;
    @NotNull(message = "Le champ prixht ne peut pas être vide")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix HT doit être supérieur à 0")
    private Double prixht;
    @DecimalMin(value = "0.0", message = "Le total de remise doit être supérieur ou égal à 0")
    private Double totalremise;
    @DecimalMin(value = "0.0", message = "Le total de TVA doit être supérieur ou égal à 0")
    private Double totaltva;
    @DecimalMin(value = "0.0", inclusive = false, message = "Le total TTC doit être supérieur à 0")
    private Double totalttc;
    private Boolean archive;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facture")
   private List<ItemFacture> items;

    @OneToOne(mappedBy = "facture")
    private Commande commande;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facture")
    private List<Paiement> paiements;




}
