package com.starwarsplanet.Planet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Table(name = Planet.TABLE_NAME)
public class Planet {
    public static final String TABLE_NAME = "planet";
    public interface CreatePlanet{

    }
    public interface UpdatePlanet{

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = { CreatePlanet.class, UpdatePlanet.class })
    @NotEmpty(groups = CreatePlanet.class)
    private String name;

    @Column(name = "climate", nullable = false)
    @NotNull(groups = { CreatePlanet.class, UpdatePlanet.class })
    @NotEmpty(groups = { CreatePlanet.class, UpdatePlanet.class })
    private String climate;

    @Column(name = "terrain", nullable = false)
    @NotNull(groups = { CreatePlanet.class, UpdatePlanet.class })
    @NotEmpty(groups = { CreatePlanet.class, UpdatePlanet.class })
    private String terrain;

    @Column(name = "films")
    private Integer films;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", climate='" + getClimate() + "'" +
            ", terrain='" + getTerrain() + "'" +
            ", films='" + getFilms() + "'" +
            "}";
    }
}