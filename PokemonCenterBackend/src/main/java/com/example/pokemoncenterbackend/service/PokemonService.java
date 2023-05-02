package com.example.pokemoncenterbackend.service;

import com.example.pokemoncenterbackend.model.Pokemon;

import java.util.List;

public interface PokemonService {
    List<Pokemon> getAll();

    Pokemon agregarPokemon(Pokemon pokemon);

    Pokemon getPokemonByNombre(String nombre);
}
