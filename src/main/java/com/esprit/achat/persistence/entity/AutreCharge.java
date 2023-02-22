package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.persistence.enumeration.OffreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "autrecharge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AutreCharge implements Serializable {
    //  -------------------oussema-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    private String nom;
    private String description;
    private Double prix;
    @Enumerated(EnumType.STRING)
    private ECharge eCharge;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "autreCharge")
    private List<DemandeAchat> demandeAchats;

}
