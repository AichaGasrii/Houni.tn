package com.esprit.achat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Service_Offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OffreService implements Serializable {
    //  -------------------aicha-------------------role:fournisseur
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "heures", nullable = false)
    private Double heures;

    @Column(name = "disponibilité", nullable = false)
    private Boolean disponibilité;

    @Column(name = "image", nullable = false)
    private String photo;

    @Column(name = "prixParHeure", nullable = false)
    private Double prixparheure;

    @ManyToOne
    @ToString.Exclude
    private AppelOffre appeloffre;

    public OffreService(String nom, Double heures, Boolean disponibilité, String photo, Double prixparheure) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("nom cannot be null or empty");
        }
        if (heures == null || heures<0) {
            throw new IllegalArgumentException("heures cannot be null and must be a non-negative value");
        }
        if (disponibilité == null || disponibilité.booleanValue()) {
            throw new IllegalArgumentException("disponibilité must be a boolean value");
        }
        if (photo == null || photo.isEmpty()) {
            throw new IllegalArgumentException("photo cannot be null or empty");
        }
        if (prixparheure == null || prixparheure < 0) {
            throw new IllegalArgumentException("prixUnitaire must be a non-negative value");
        }

        this.nom = nom;
        this.heures = heures;
        this.disponibilité = disponibilité;
        this.photo = photo;
        this.prixparheure = prixparheure;

    }
}