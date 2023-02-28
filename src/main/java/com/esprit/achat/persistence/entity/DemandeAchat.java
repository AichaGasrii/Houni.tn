package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.OffreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    //  -------------------aicha-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id ;
    private String nom;
    private String objet;
    private String description;
    private Integer quantiteMin;
    @Enumerated(EnumType.STRING)
    private OffreType offreType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "demandeAchat")
    private List<AppelOffre> appelOffres;


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private AutreCharge autreCharge;

}
