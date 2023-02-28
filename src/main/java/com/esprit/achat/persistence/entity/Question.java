package com.esprit.achat.persistence.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Question implements Serializable {
    //  -------------------moslem-------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQ;
    private String textQ;


}
