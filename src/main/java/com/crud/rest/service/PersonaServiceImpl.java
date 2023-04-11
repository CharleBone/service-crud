package com.crud.rest.service;

import com.crud.rest.entity.Estadistica;
import com.crud.rest.entity.Persona;
import com.crud.rest.entity.RelacionFamiliar;
import com.crud.rest.exceptions.BadRequestException;
import com.crud.rest.exceptions.NotFoundException;
import com.crud.rest.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaServiceImpl implements IPersonaService {

    public static final String MASCULINO = "M";
    public static final String FEMENINO = "F";

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        List<Persona> personas = personaRepository.findAll();
        return personas;
    }

    @Override
    public Persona findById(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (!persona.isPresent()) {
            throw new NotFoundException("No existe la persona en la base de datos");
        }
        return persona.get();
    }

    @Override
    public Persona save(Persona persona) {
        //toUpperCase(persona);
        Optional<Persona> personaOptional = Optional.of(persona);
        validateParams(personaOptional);
        return personaRepository.save(personaOptional.get());
    }

    private void toUpperCase(Persona persona) {
        persona.setTipoDocumento(persona.getTipoDocumento().toUpperCase(Locale.ROOT));
        persona.setPais(persona.getPais().toUpperCase(Locale.ROOT));
        persona.setSexo(persona.getSexo().toUpperCase(Locale.ROOT));
    }

    private void validateParams(Optional<Persona> personaOptional) {
        if (existenLaPersona(personaOptional.get().getNumeroDni())) {
            throw new BadRequestException("No pueden existir personas con el mismo numero de DNI");
        }
        if(Integer.parseInt(personaOptional.get().getEdad()) < 18) {
            throw new BadRequestException("No pueden existir personas Menores de 18 años");
        }
    }

    @Override
    public Persona update(Long id, Persona persona) {
        Optional<Persona> personaActual = Optional.of(findById(id));
        personaActual.get().setEmail(persona.getEmail());
        personaActual.get().setPais(persona.getPais());
        personaActual.get().setSexo(persona.getSexo());
        personaActual.get().setTelefono(persona.getTelefono());
        return personaRepository.save(personaActual.get());
    }

    @Override
    public void updateRelacion(Persona persona, RelacionFamiliar relacionFamiliar) {
        persona.getRelaciones().add(relacionFamiliar);
        personaRepository.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public int cantidadPersonasPorGenero(String genero) {
        return personaRepository.personasPorGenero(genero);
    }

    @Override
    public Long porcentajeDeArgentinos() {
        List<Persona> personas = findAll();
        Long cantidadArg = personas.stream().filter(persona -> Objects.equals(persona.getPais(), "ARGENTINA")).count();
        if (personas.isEmpty() || cantidadArg == 0) {
            return 0L;
        }
        cantidadArg = cantidadArg * 100 / personas.size();
        return cantidadArg;
    }

    public boolean existenLaPersona(String numeroDni) {
        return personaRepository.buscarCoincidencia(numeroDni);
    }

    @Override
    public Map<String, Object> estadisticasTotales() {
        Map<String, Object> estadisticas = new HashMap<>();
        List<Estadistica> estadisticasTotales = personaRepository.obtenerEstadisticas();

        estadisticasTotales.forEach(estadistica -> estadisticas.put("cantidad_de_personas_".concat(estadistica.getSexo()), estadistica.getCantidad()));

        estadisticas.put("porcentaje_argentinos", porcentajeDeArgentinos());

        return estadisticas;
    }

    @Override
    public void deleteAllRelaciones() {
        List<Persona> personas = personaRepository.findAll();
        personas.forEach(persona -> persona.getRelaciones().clear());
    }

}
