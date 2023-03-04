package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.HoraireTravail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livreur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private double MoyenneNote ;
    //   private Integer NoteLivraison ;
    @Enumerated(EnumType.STRING)
    private HoraireTravail horaireTravail;
    private String telephone;
    private boolean disponible;
    private Integer DisLikes=0;

    private LocalDate dateCreation ;
    private Integer Likes=0;
    private boolean prime ;

    private LocalDate DateMoyenneSuperieure ;

    @JsonIgnore
    @OneToMany(mappedBy = "livreur")
    private List<Facture> facture;
    @JsonIgnore
    @OneToMany(mappedBy = "livreur")
    private List<Livraison> livraisons = new ArrayList<>();

}






