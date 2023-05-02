package com.example.pokemoncenterbackend.dao;

import com.example.pokemoncenterbackend.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokemonDaoImpl implements PokemonDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Pokemon> getAll() {
        return mongoTemplate.findAll(Pokemon.class);
    }

    @Override
    public Pokemon getPokemonByNombre(String nombre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nombre").is(nombre));
        return mongoTemplate.findOne(query, Pokemon.class);
    }

    @Override
    public void agregarPokemon(Pokemon pokemon) {
        mongoTemplate.save(pokemon);
    }
}
