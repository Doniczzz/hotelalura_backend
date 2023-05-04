package com.gabrielvento.hotelalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Getter @Setter @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter @Column(name = "username")
    private String username;

    @Getter @Setter @Column(name = "password")
    private String password;

}
