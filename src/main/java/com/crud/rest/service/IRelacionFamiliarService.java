package com.crud.rest.service;

import java.util.Map;

public interface IRelacionFamiliarService {

    void addRelacion(Long idPersona, Long idFamiliar, String tipoRelacion);

    Map<String, String> getRelaciones(Long id);

    void deleteAll();
}
