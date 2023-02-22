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
    //  -------------------aicha-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    private String nom;
    private String objet;
    private String description;
    private Double prix;
    private Integer quantiteMin;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private DemandeAchat demandeAchat;

    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Unité unité;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private NatureArticle natureArticle;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "appeloffre")
    @ToString.Exclude
    private List<OffreProduit> offreProduits;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "appeloffre")
    @ToString.Exclude
    private List<OffreService> offreServices;



}
