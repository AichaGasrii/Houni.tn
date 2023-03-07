package com.esprit.achat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "naturearticle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NatureArticle implements Serializable {
    //  -------------------aicha-------------------role:operateur
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "secteur", nullable = false, unique = true)
    private String secteur;

    @Column(name = "description", nullable = false)
    private String description;

    private String unité;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "natureArticle")
    private List<AppelOffre> appelOffres;


    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<OffreProduit> offreProduits;



}