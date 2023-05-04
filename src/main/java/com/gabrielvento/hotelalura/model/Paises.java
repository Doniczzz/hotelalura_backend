package com.gabrielvento.hotelalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paises")
public class Paises {

    @Getter @Setter @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter @Column(name = "Name")
    private String name;

    public Paises() {
    }
}
