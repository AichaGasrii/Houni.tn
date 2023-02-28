package com.esprit.achat.persistence.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table( uniqueConstraints = {
        @UniqueConstraint(columnNames = {"OffreProduitId", "user_id"})
})
public class Rating implements Serializable {
    //  -------------------moslem-------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int rate;
    @ManyToOne
    @JoinColumn(name = "OffreProduitId", nullable = false)
    @JsonIgnore
    private OffreProduit OffreProduit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


}
