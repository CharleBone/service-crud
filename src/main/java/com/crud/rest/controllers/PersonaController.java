package com.crud.rest.controllers;

import com.crud.rest.entity.Persona;
import com.crud.rest.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/personas")
    public List<Persona> findAll() {
        return personaService.findAll();
    }

    @GetMapping("/personas/{id}")
    public Persona findById(@PathVariable() Long id) {
        return personaService.findById(id);
    }

    @PostMapping("/personas")
    public Persona save(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @PutMapping("/personas/{id}")
    public Persona update(@RequestBody Persona persona, @PathVariable() Long id) {
        return personaService.update(id, persona);
    }

    @DeleteMapping("/personas/{id}")
    public void deleteById(@PathVariable() Long id) {
        personaService.deleteById(id);
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<?> estadisticasTotales() {
        return new ResponseEntity<>(personaService.estadisticasTotales(), HttpStatus.OK);
    }
}
