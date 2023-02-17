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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Double quantite;
    private Boolean disponibilité;
    private String photo;
    private Double prixUnitaire;

   /* @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "natureArticleP", updatable = false)
    private NatureArticle natureArticleP;

    @ManyToMany(mappedBy = "offreProduits")
    @ToString.Exclude
    @JsonIgnore
    private List<AppelOffre> appelOffres;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Unité> unitésProduit;

    */



}
