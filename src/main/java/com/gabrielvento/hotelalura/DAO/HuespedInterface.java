package com.gabrielvento.hotelalura.DAO;

import com.gabrielvento.hotelalura.model.Huesped;

import java.util.List;

public interface HuespedInterface {

    List<Huesped> obtenerHuespedes();

    long obtenerCantidadHuespedes();

    Huesped obtenerHuespedPorNombre(String nombre);

    void eliminarHuesped(long id);

    void guardarHuesped(Huesped huesped);

    void actualizarHuesped(long id, Huesped huesped);

    Huesped obtenerHuespedPorId(long id);
}
