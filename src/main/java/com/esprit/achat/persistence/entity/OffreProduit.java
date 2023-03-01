package com.esprit.achat.persistence.entity;

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
    private Integer id;
    private String nom;
    private Double quantite;
    private Boolean disponibilité;
    private String photo;
    private Double prixUnitaire;
    @ManyToOne
    @ToString.Exclude
    private AppelOffre appeloffre;

    @OneToMany(mappedBy = "offreProduit", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<NatureArticle> natureArticles;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OffreProduit")
    private List<Rating> ratings;



}

