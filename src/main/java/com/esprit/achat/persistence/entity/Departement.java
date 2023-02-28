package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.DepartementTYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departement {
    //  -------------------oussema-------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Nom;
    private Integer CodeDep;
    private String Description ;

      @Enumerated(EnumType.STRING)
      private DepartementTYPE departementTYPE ;
}
