package com.example.pokemoncenterbackend.dao;

import com.example.pokemoncenterbackend.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon,String> {
    Pokemon findByNombre(String nombre);
}
