package ru.ermakow.limitmodule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "limits")
public class LimitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "limits_id_seq")
    @SequenceGenerator(name = "limits_id_seq", sequenceName = "limits_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "day_limit")
    private BigDecimal dayLimit;
}
