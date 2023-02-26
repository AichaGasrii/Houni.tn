package com.esprit.achat.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "codepromo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CodePromo implements Serializable {
    //  -------------------salma-------------------
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    private Double remise;
}
