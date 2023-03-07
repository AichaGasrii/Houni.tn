package com.esprit.achat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;
import javax.validation.constraints.*;

@Entity
@Table(name = "AppelOffre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AppelOffre implements Serializable {
    //  -------------------aicha-------------------role:fournisseur
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String nom;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String objet;

    @Size(max = 10, message = "Le champ code doit avoir au maximum 10 caractères")
    @NotBlank(message = "ce champ ne doit pas être vide")
    private String description;

    private Double prixTotal;

    @Column(name = "quantiteMin", nullable = false, unique = true)
    private Integer quantiteMin;

    private Boolean accepte;

    public AppelOffre( Integer quantiteMin) {


        if (quantiteMin == null || quantiteMin < 0) {
            throw new IllegalArgumentException("quantiteMin must be a non-negative value");
        }

        this.quantiteMin = quantiteMin;

    }
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private DemandeAchat demandeAchat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private NatureArticle natureArticle;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "appeloffre")
    @ToString.Exclude
    private List<OffreProduit> offreProduits;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "appeloffre")
    private List<OffreService> offreServices;

}