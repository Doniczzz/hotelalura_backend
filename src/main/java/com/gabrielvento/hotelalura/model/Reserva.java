package com.gabrielvento.hotelalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Getter @Setter @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter @Column(name = "fechaIngreso")
    private String fechaIngreso;

    @Getter @Setter @Column(name = "fechaEgreso")
    private String fechaEgreso;

    @Getter @Setter @Column(name = "valor")
    private double valor;

    @Getter @Setter @Column(name = "formaPago")
    private String formaPago;

    public Reserva() {
    }

}
