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
    protected Integer id = 0;
    private String nom;
    private String objet;
    private String description;
    private Double prix;
    private Integer quantiteMin;

   /* @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "appeloffre", updatable = false)
    private DemandeAchat demandeAchat;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<OffreProduit> offreProduits;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<OffreService> offreServices

    */


}
