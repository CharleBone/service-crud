package com.crud.rest.repository;

import com.crud.rest.entity.Estadistica;
import com.crud.rest.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("SELECT COUNT(P) FROM Persona P WHERE P.sexo = :genero ")
    int personasPorGenero(@Param("genero") String genero);

    @Query("SELECT COUNT(P) FROM Persona P WHERE P.pais = 'Argentina' ")
    int personasDeArgentina();

    @Query("SELECT CASE WHEN COUNT(e.id) > 0 THEN true ELSE false END FROM Persona e WHERE e.numeroDni = :numeroDni")
    boolean buscarCoincidencia(@Param("numeroDni") String numeroDni);

    @Query("SELECT new com.crud.rest.entity.Estadistica(COUNT(P), P.sexo) FROM Persona P GROUP BY P.sexo")
    List<Estadistica> obtenerEstadisticas();

}