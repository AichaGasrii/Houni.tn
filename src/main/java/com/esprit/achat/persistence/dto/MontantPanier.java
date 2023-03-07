package com.esprit.achat.persistence.dto;

import com.esprit.achat.persistence.entity.ItemCommande;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MontantPanier {
    private Double montantTotalHT = 0d;
    private Double Remise = 0d;
    private Double TVA = 0d;
    private Double montantTotalAPayer = 0d;
}
