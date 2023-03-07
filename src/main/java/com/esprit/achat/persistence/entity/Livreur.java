package com.esprit.achat.persistence.entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import com.esprit.achat.persistence.enumeration.Disponibilite;
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
import java.util.Set;


@Entity
@Table(name = "Deliveryman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livreur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String nom;
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String prenom;

    @PositiveOrZero
    private double MoyenneNote ;
    //   private Integer NoteLivraison ;
    @Enumerated(EnumType.STRING)
    private HoraireTravail horaireTravail;

    @NotNull
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^\\+?[0-9\\s]*$")
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Disponibilite disponible;
    @NotNull
    @PositiveOrZero
    private Integer DisLikes=0;

    private LocalDate dateCreation ;
    @NotNull
    @PositiveOrZero
    private Integer Likes=0;
    private boolean prime ;
    private LocalDate DateMoyenneSuperieure ;

    @JsonIgnore
    @OneToMany(mappedBy = "livreur")
    private List<Facture> facture;
    @JsonIgnore
    @OneToMany(mappedBy = "livreur")
    private List<Livraison> livraisons = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

}






