package com.crud.rest.service;

import com.crud.rest.entity.Persona;
import com.crud.rest.entity.RelacionFamiliar;

import java.util.List;
import java.util.Map;

public interface IPersonaService {

    List<Persona> findAll();

    Persona findById(Long id);

    Persona save(Persona persona);

    Persona update(Long id, Persona persona);

    void updateRelacion(Persona persona, RelacionFamiliar relacionFamiliar);

    void deleteById(Long id);

    int cantidadPersonasPorGenero(String genero);

    Long porcentajeDeArgentinos();

    boolean existenLaPersona(String numeroDni);

    Map<String, Object> estadisticasTotales();

    void deleteAllRelaciones();
}