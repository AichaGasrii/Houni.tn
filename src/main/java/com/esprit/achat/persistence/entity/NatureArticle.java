package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "naturearticle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NatureArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String secteur;
    private String description;

   /*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "natureArticleP")

    private List<OffreProduit> offreProduits;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "natureArticleS")
    private List<OffreService> offreServices;

    */
}
