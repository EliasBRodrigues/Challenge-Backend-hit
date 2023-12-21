package com.starwarsplanet.Planet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starwarsplanet.Planet.model.Planet;
import com.starwarsplanet.Planet.model.Planet.CreatePlanet;
import com.starwarsplanet.Planet.service.PlanetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("")
    public List<Planet> retrivePlanet(){
        return planetService.findAllPlanet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> findById(@PathVariable Long id){
        Planet planet = this.planetService.findPlanetById(id);
        return ResponseEntity.ok().body(planet);
    }

    @PostMapping("")
    @Validated(CreatePlanet.class)
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet){
        this.planetService.createPlanet(planet);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(planet.getId())
                    .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/")
    //@Validated(UpdatePlanet.class)
    // public ResponseEntity<Planet> updatePlanet(@Valid @RequestBody Planet planet, @PathVariable Long id){
    //     planet.setId(id);
    //     this.planetService.updatePlanet(planet);
    //     return ResponseEntity.noContent().build();
    // }
    public Planet updatePlanet(@RequestBody Planet planet){
        return planetService.updatePlanet(planet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.planetService.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}