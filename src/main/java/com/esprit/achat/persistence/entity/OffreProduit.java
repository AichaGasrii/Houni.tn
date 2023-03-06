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
    //  -------------------aicha-------------------
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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "OffreProduit")

    private List<Rating> ratings;



}
