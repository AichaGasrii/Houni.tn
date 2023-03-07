package com.esprit.achat.persistence.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Rating implements Serializable {
    //  -------------------moslem-------------------
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rate;
    @ManyToOne

    private OffreProduit offreProduit;

    @ManyToOne
    private User user;


}
