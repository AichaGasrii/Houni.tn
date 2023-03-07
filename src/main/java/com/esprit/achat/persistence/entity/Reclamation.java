package com.esprit.achat.persistence.entity;

import com.esprit.achat.persistence.enumeration.ReclamationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class Reclamation implements Serializable {
    //  -------------------moslem-------------------

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;

    private String email;
    @Enumerated(EnumType.STRING)
    private ReclamationType reclamationType;

    @ManyToOne

    private Commande commande;
     @ManyToOne

     private User user;

}
