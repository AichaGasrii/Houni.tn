package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.dto.ValidAdress;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Credit_Invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FactureAvoir implements Serializable {
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
   // @DecimalMin(value = "0.0", inclusive = false, message = "Le total TTC doit être supérieur à 0")
    private Double totalttc;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "factureAvoir_id")
    private List<ItemFactureAvoir> items = new ArrayList<>();

}
