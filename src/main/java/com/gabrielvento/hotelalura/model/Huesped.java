package com.gabrielvento.hotelalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "huespedes")
public class Huesped {

    @Getter
    @Setter
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "nacionalidad")
    private String nacionalidad;

    @Getter @Setter @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @ManyToOne @JoinColumn(name = "reserva_id")
    private Reserva reservaId;
}
