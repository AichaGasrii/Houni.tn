package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.dto.ValidCountry;
import com.esprit.achat.persistence.enumeration.Methode;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import javax.validation.ConstraintValidator;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Paiement implements Serializable {
    //  -------------------salma-------------------role:user
    /*
    @NotNull : Cette annotation est utilisée pour s'assurer que le prix n'est pas nul.
    @PositiveOrZero : Cette annotation est utilisée pour s'assurer que le prix est un nombre positif ou zéro.
    @ValidCountry : Cette annotation est utilisée pour valider que le pays est un pays valide en utilisant une annotation personnalisée.
    @Pattern : Cette annotation est utilisée pour s'assurer que le pays est une chaîne de caractères commençant par une lettre majuscule suivie de lettres minuscules ou d'espaces.
    @NotBlank : Cette annotation est utilisée pour s'assurer que la devise, la méthode et la description ne sont pas vides.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id = 0;
   // @NotNull(message = "Le prix ne peut pas être nul")
 //   @PositiveOrZero(message = "Le prix doit être positif ou zéro")
    private Double prix;

    @ValidCountry
  //  @Pattern(regexp = "[A-Z][a-zA-Z ]*", message = "Le pays doit être une chaîne de caractères commençant par une lettre majuscule suivie de lettres minuscules ou d'espaces")
    private String pays;

//    @NotBlank(message = "La devise ne peut pas être vide")
    private String devise;

    @Enumerated(EnumType.STRING)
    private Methode methode;

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Facture facture;

}
