package com.starwarsplanet.Planet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwarsplanet.Planet.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long>{

}