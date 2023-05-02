package com.example.pokemoncenterbackend.controller;

import com.example.pokemoncenterbackend.model.Pokemon;
import com.example.pokemoncenterbackend.service.PokemonService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @GetMapping(value = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon getPokemonByNombre(@PathVariable String nombre) {
        return pokemonService.getPokemonByNombre(nombre);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pokemon> getAll() {
        return pokemonService.getAll();
    }

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> addPokemon(@RequestParam("imagen") MultipartFile imagen, @RequestParam("nombre") String nombre, @RequestParam("tipo") String tipo, @RequestParam("nivel") String nivel, @RequestParam("descripcion") String descripcion) {
        Pokemon pokemon = new Pokemon();
        pokemon.setNombre(nombre);
        pokemon.setTipo(tipo);
        pokemon.setNivel(nivel);
        pokemon.setDescripcion(descripcion);

        try {
            byte[] imagenBytes = imagen.getBytes();
            String fileName = pokemon.getNombre() + ".jpg";
            ObjectId imagenId = gridFsTemplate.store(new ByteArrayInputStream(imagenBytes), fileName, imagen.getContentType());
            String imagenIdStr = imagenId.toString();
            pokemon.setImagenId(imagenIdStr);

            String imagenUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("Pokemon/img/pokemon/" + nombre).toUriString();
            pokemon.setImagenUrl(imagenUrl);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

        Pokemon pokemonAgregado = pokemonService.agregarPokemon(pokemon);

        return ResponseEntity.ok().body(pokemonAgregado);
    }

    @GetMapping(value = "/img/pokemon/{nombre}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImagen(@PathVariable String nombre) {
        // Buscar el Pokemon por nombre para obtener el ID de la imagen
        Pokemon pokemon = pokemonService.getPokemonByNombre(nombre);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }
        String imagenId = pokemon.getImagenId();

        GridFSFile archivo = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(imagenId)));
        if (archivo == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener el contenido del archivo de imagen
        try {
            GridFsResource resource = gridFsTemplate.getResource(archivo);
            byte[] imagenBytes = IOUtils.toByteArray(resource.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagenBytes);
        } catch (IOException e) {
            // Manejar excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
