package com.esprit.achat.persistence.dto;

import com.esprit.achat.persistence.entity.ItemCommande;
import lombok.*;


import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Panier {
    private String clientcin;
    private List<ItemCommande> items;

    private String codePromo;
}
