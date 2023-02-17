package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.OffreType;
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
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id = 0;
    private String nom;
    private String objet;
    private String description;
    private Integer quantiteMin;
    @Enumerated(EnumType.STRING)
    private OffreType offreType;

   /* @OneToMany(fetch = FetchType.EAGER, mappedBy = "demandeAchat")
    private List<AppelOffre> appelOffres;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "demandeAchat", updatable = false)
    private AutreCharge autreCharge;

    */
}
