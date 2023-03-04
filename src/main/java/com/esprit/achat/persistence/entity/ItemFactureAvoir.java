package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_factureavoir")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemFactureAvoir implements Serializable {
    //  -------------------salma-------------------role:fournisseur
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Column(name = "TVA", nullable = false)
    private Double tva;

    @Column(name = "PRICE_HT", nullable = false)
    private Double priceHt;

    @Column(name = "MONTANT_HT", nullable = false)
    private Double montantHt;

    @Column(name = "MONTANT_TTC", nullable = false)
    private Double montantTtc;

    public ItemFactureAvoir(String code, String label, Double quantity, Double tva, Double priceHt) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty");
        }
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("Label cannot be null or empty");
        }
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("Quantity must be a non-negative value");
        }
        if (tva == null || tva < 0) {
            throw new IllegalArgumentException("TVA must be a non-negative value");
        }
        if (priceHt == null || priceHt < 0) {
            throw new IllegalArgumentException("Price HT must be a non-negative value");
        }

        this.code = code;
        this.label = label;
        this.quantity = quantity;
        this.tva = tva;
        this.priceHt = priceHt;

    }
    @PrePersist
    private void calculateMontants() {
        this.montantHt = this.quantity * this.priceHt;
        this.montantTtc = this.montantHt + (this.montantHt * this.tva / 100);}
    // getters and setters


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "facture_id", updatable = false)
    private FactureAvoir factureAvoir;
    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private OffreProduit offreProduit;

    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private OffreService offreService;
}
