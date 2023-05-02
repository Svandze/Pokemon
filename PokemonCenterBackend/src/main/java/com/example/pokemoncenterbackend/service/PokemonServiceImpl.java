package com.example.pokemoncenterbackend.service;

import com.example.pokemoncenterbackend.dao.PokemonRepository;
import com.example.pokemoncenterbackend.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon agregarPokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
        return pokemon;
    }

    @Override
    public Pokemon getPokemonByNombre(String nombre) {
        return pokemonRepository.findByNombre(nombre);
    }
}
