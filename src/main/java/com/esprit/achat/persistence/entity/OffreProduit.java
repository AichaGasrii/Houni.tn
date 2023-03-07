package com.esprit.achat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "offreProduit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OffreProduit implements Serializable {
    //  -------------------aicha-------------------role:fournisseur
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @Column(name = "quantite", nullable = false, unique = true)
    private Double quantite;

    @Column(name = "disponibilité", nullable = false, unique = true)
    private Boolean disponibilité;

    @Column(name = "image", nullable = false, unique = true)
    private String photo;

    @Column(name = "prixUnitaire", nullable = false, unique = true)
    private Double prixUnitaire;
    @ManyToOne
    @ToString.Exclude
    private AppelOffre appeloffre;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OffreProduit")
    private List<Rating> ratings;

    public OffreProduit(String nom, Double quantite, Boolean disponibilité, String photo, Double prixUnitaire) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("nom cannot be null or empty");
        }
        if (quantite == null || quantite<0) {
            throw new IllegalArgumentException("quantite cannot be null and must be a non-negative value");
        }
        if (disponibilité == null || disponibilité.booleanValue()) {
            throw new IllegalArgumentException("disponibilité must be a boolean value");
        }
        if (photo == null || photo.isEmpty()) {
            throw new IllegalArgumentException("photo cannot be null or empty");
        }
        if (prixUnitaire == null || prixUnitaire < 0) {
            throw new IllegalArgumentException("prixUnitaire must be a non-negative value");
        }

        this.nom = nom;
        this.quantite = quantite;
        this.disponibilité = disponibilité;
        this.photo = photo;
        this.prixUnitaire = prixUnitaire;

    }
}


