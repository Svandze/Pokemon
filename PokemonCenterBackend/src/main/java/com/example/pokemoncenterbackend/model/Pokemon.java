package com.example.pokemoncenterbackend.model;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pokemon")
public class Pokemon {
    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String nivel;
    private String descripcion;
    private String imagenId;
    private String imagenUrl;

}
