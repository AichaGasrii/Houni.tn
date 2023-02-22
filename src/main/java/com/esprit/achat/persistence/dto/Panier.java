package com.esprit.achat.persistence.dto;

import com.esprit.achat.persistence.entity.ItemCommande;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Panier {

    private String client;
    private List<ItemCommande> items;

    private String codePromo;
}
