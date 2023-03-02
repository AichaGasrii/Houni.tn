package com.esprit.achat.persistence.dto;

import com.esprit.achat.persistence.entity.ItemCommande;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@PreAuthorize("hasRole('User')")
public class Panier {
    private String clientcin;
    private List<ItemCommande> items;

    private String codePromo;
}
