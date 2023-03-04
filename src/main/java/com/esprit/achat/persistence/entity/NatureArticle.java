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
    //  -------------------aicha-------------------
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String secteur;
    private String description;
    private String unité;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "natureArticle")
    private List<AppelOffre> appelOffres;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OffreProduit offreProduit;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Unité unite;




}