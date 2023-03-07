package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
@Entity
@Table(name = "Promo_Code")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CodePromo implements Serializable {
    //  -------------------salma-------------------role:operateur
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Max(99)
    private Double remise;
}
