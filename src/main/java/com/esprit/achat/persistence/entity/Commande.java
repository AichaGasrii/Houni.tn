package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id = 0;
    private String client;
    private String reponsableclient;
    private String adresseclient;
    private String devise;
    @Temporal(TemporalType.DATE)
    private Date datefacture;
    private Double prixht;
    private Double totalremise;
    private Double totaltva;
    private Double totalttc;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "commande")
    private List<ItemCommande> items;
}
