package com.esprit.achat.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Reaction implements Serializable {
    //  -------------------moslem-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idReaction;
    private String react;

}
