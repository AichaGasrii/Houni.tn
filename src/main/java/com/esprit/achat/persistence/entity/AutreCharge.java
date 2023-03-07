package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.persistence.enumeration.OffreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Other_Charge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AutreCharge implements Serializable {
    //  -------------------oussema-------------------role:user
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @NotBlank(message = "ce champ ne doit pas être vide")
    private String nom;
    @Size(max = 10, message = "Le champ code doit avoir au maximum 10 caractères")
    @NotBlank(message = "ce champ ne doit pas être vide")
    private String description;

    @NotNull(message = "Le champ prix ne peut pas être vide")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    private Double prix;

    @Enumerated(EnumType.STRING)
    private ECharge eCharge;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "autreCharge")
    private List<DemandeAchat> demandeAchats;

}