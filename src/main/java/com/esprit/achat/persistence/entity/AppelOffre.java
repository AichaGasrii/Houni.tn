package com.esprit.achat.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "appeloffre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AppelOffre implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    private String nom;
    private String objet;
    private String description;
    private Double prix;
    private Integer quantiteMin;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "appeloffre", updatable = false)
    private DemandeAchat demandeAchat;

    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Unité unité;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "natureArticle", updatable = false)
    @JsonIgnore
    private NatureArticle natureArticle;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<OffreProduit> offreProduits;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<OffreService> offreServices;



}
