package com.crud.rest.service;

import com.crud.rest.entity.Persona;
import com.crud.rest.entity.RelacionFamiliar;
import com.crud.rest.exceptions.BadRequestException;
import com.crud.rest.exceptions.NotFoundException;
import com.crud.rest.repository.RelacionFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelacionFamiliarImpl implements IRelacionFamiliarService {

    private static List<String> tipoRelaciones = Arrays.asList("PADRE", "MADRE", "PRIM@", "TI@", "HERMAN@");

    @Autowired
    private RelacionFamiliarRepository relacionFamiliarRepository;

    @Autowired
    private IPersonaService personaService;

    @Override
    public void addRelacion(Long idPersona, Long idFamiliar, String tipoRelacion) {
        Optional<Persona> persona = Optional.of(personaService.findById(idPersona));
        Optional<Persona> familiar = Optional.of(personaService.findById(idFamiliar));
        if (!tipoRelaciones.contains(tipoRelacion)) {
            throw new BadRequestException("El tipo de relacion no es valido. ");
        }

        validateMatch(persona.get().getRelaciones(), familiar.get().getId());
        RelacionFamiliar relacionFamiliar = new RelacionFamiliar();
        relacionFamiliar.setPersonaId(persona.get().getId());
        relacionFamiliar.setFamiliarId(familiar.get().getId());
        relacionFamiliar.setTipo(tipoRelacion);

        personaService.updateRelacion(persona.get(), relacionFamiliar);
        relacionFamiliarRepository.save(relacionFamiliar);
    }

    private void validateMatch(List<RelacionFamiliar> relaciones, Long familiar) {
        boolean match = relaciones
                .stream().anyMatch(relacionFamiliar -> relacionFamiliar.getFamiliarId().equals(familiar));
        if (match) {
            throw new NotFoundException("Ya existe una relacion, No puede haber mas.. sorry :( ");
        }
    }

    @Override
    public Map<String, String> getRelaciones(Long id) {
        Optional<Persona> persona = Optional.of(personaService.findById(id));
        if (persona.get().getRelaciones().isEmpty()) {
            throw new NotFoundException("La Persona no tiene relaciones familiares");
        }
        Map<String, String> relaciones = new HashMap<>();
        relaciones.put("Id", persona.get().getId().toString());
        relaciones.put("Nombre", persona.get().getId().toString());
        persona.get().getRelaciones().forEach(relacionFamiliar -> {
            Optional<Persona> familiar = Optional.of(personaService.findById(relacionFamiliar.getFamiliarId()));
            relaciones.put("ES ".concat(relacionFamiliar.getTipo()).concat(" DE "), familiar.get().getNombre().toUpperCase(Locale.ROOT));
        });

        return relaciones;
    }

    @Override
    public void deleteAll() {
        relacionFamiliarRepository.deleteAll();
        personaService.deleteAllRelaciones();
    }
}
