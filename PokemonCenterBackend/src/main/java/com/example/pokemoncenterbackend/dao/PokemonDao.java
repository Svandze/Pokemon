package com.example.pokemoncenterbackend.dao;

import com.example.pokemoncenterbackend.model.Pokemon;

import java.util.List;

public interface PokemonDao {
    List<Pokemon> getAll();

    Pokemon getPokemonByNombre(String nombre);

    void agregarPokemon (Pokemon pokemon);
}
