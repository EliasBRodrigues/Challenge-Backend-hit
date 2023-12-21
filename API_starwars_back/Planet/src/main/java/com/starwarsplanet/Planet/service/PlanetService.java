package com.starwarsplanet.Planet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwarsplanet.Planet.model.Planet;
import com.starwarsplanet.Planet.repository.PlanetRepository;

import jakarta.transaction.Transactional;

@Service
public class PlanetService {
    
    @Autowired
    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> findAllPlanet(){
        return planetRepository.findAll();
    }

    // get planet by ID
    public Planet findPlanetById(Long id){
        Optional<Planet> planet = this.planetRepository.findById(id);
        return planet.orElseThrow(() -> new RuntimeException("Planeta nao encontrado" + id));
    }

    //change directly in database: create planet
    @Transactional
    public Planet createPlanet(Planet obj){
        obj.setId(null);
        obj = this.planetRepository.save(obj);
        return obj;        
    }
    //change directly in database: update planet
    @Transactional
    public Planet updatePlanet(Planet obj){
        Planet newPlanet = findPlanetById(obj.getId());
        newPlanet.setName(obj.getName());
        newPlanet.setClimate(obj.getClimate());
        newPlanet.setTerrain(obj.getTerrain());
        newPlanet.setFilms(obj.getFilms());
        return this.planetRepository.save(newPlanet);
    }

    @Transactional
    public void deletePlanet(Long id){
        findPlanetById(id);
        try {
            this.planetRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("nao eh possivel excluir");
        }
    }   
}