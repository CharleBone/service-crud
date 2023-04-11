package com.crud.rest.controllers;

import com.crud.rest.service.IRelacionFamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/relacionFamiliar")
public class RelacionFamiliarController {

    @Autowired
    private IRelacionFamiliarService relacionFamiliarService;

    @PostMapping("personas/{id}/vinculos/{vinculo}/{idFamiliar}")
    public void addRelacionFamiliar(@PathVariable Long id, @PathVariable String vinculo, @PathVariable Long idFamiliar){
        relacionFamiliarService.addRelacion(id, idFamiliar, vinculo);
    }

    @GetMapping("/relaciones/{id}")
    public Map<String, String> getRelaciones(@PathVariable Long id){
        return relacionFamiliarService.getRelaciones(id);
    }

    @DeleteMapping("/relaciones")
    public void deleteAll(){
        relacionFamiliarService.deleteAll();
    }


}
