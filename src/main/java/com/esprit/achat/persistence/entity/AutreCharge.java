package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.ECharge;
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
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id = 0;
    private String nom;
    private String description;
    private Double prix;
    @Enumerated(EnumType.STRING)
    private ECharge eCharge;

     /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "autreCharge")
    private List<DemandeAchat> demandeAchats;

      */

}
