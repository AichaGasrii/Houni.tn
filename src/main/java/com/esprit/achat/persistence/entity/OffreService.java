package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offreService")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OffreService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Double heures;
    private Boolean disponibilité;
    private String photo;
    private Double prixparheure;


   /* @ManyToMany(mappedBy = "offreServices")
    @ToString.Exclude
    @JsonIgnore
    private List<AppelOffre> appelOffres;

    */


}