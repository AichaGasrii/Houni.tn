package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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


   /* @ManyToMany(mappedBy = "offreProduits")
    @ToString.Exclude
    @JsonIgnore
    private List<AppelOffre> appelOffres;

    */


}
