package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.OffreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "demandeachat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DemandeAchat implements Serializable {
    //  -------------------aicha-------------------role:user
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id ;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String nom;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String objet;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String description;

    @Column(name = "quantiteMin", nullable = false, unique = true)
    private Integer quantiteMin;

    @Enumerated(EnumType.STRING)
    private OffreType offreType;

    public DemandeAchat( Integer quantiteMin) {


        if (quantiteMin == null || quantiteMin < 0) {
            throw new IllegalArgumentException("quantiteMin must be a non-negative value");
        }

        this.quantiteMin = quantiteMin;

    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "demandeAchat")
    private List<AppelOffre> appelOffres;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private AutreCharge autreCharge;

}
