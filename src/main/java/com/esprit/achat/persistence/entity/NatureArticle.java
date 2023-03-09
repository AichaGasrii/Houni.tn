package com.esprit.achat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
//@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NatureArticle implements Serializable {
    //  -------------------aicha-------------------role:operateur
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Column(name = "secteur", nullable = false, unique = true)
    private String secteur;
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 10, message = "Le champ code doit avoir au maximum 10 caractères")
    @Column(name = "description", nullable = false)
    private String description;
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String unité;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "natureArticle")
    private List<AppelOffre> appelOffres;


    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<OffreProduit> offreProduits;



}