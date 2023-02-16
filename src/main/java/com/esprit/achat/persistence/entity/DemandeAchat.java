package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.OffreType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
}
