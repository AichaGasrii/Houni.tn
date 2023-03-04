package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.persistence.enumeration.Etat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

@Entity
@Table(name = "commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Commande implements Serializable {
    //  -------------------salma-------------------role:operateur
    /*
    @NotNull : indique que le champ ne peut pas être nul ou vide.
    @PastOrPresent : indique que la date doit être dans le passé ou le présent.
    @DecimalMin : indique que la valeur minimale autorisée pour le champ est de 0,
 avec des options supplémentaires pour spécifier si 0 est inclus ou non.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "cin")
    @Pattern(regexp = "[0-9]{8}", message = "Le CIN doit être composé de 8 chiffres")
    @NotBlank(message = "ce champ ne doit pas être vide")
    private String clientcin;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String reponsableclient;

    @NotBlank(message = "ce champ ne doit pas être vide")
    @ValidAdress
    private String adresseclient;

   // @NotBlank(message = "Devise est obligatoire")
    private String devise;

    @NotNull(message = "Le champ datefacture ne peut pas être vide")
    @PastOrPresent(message = "La date de la commande doit être dans le passé ou le présent")
    @CreationTimestamp
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;


    @NotNull(message = "Le champ prixht ne peut pas être vide")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix HT doit être supérieur à 0")
    private Double prixht;
    @DecimalMin(value = "0.0", message = "Le total de remise doit être supérieur ou égal à 0")
    private Double totalremise;
    @DecimalMin(value = "0.0", message = "Le total de TVA doit être supérieur ou égal à 0")
    private Double totaltva;
    @DecimalMin(value = "0.0", inclusive = false, message = "Le total TTC doit être supérieur à 0")
    private Double totalttc;

    @Enumerated(EnumType.STRING)
    private Etat etat;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
    private List<ItemCommande> items;

    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Facture facture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
    private List<Reclamation> reclamations;

}