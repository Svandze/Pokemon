<template>
    <form class="pokemon-form" @submit.prevent="submitForm">
      <label>
        Nombre:
        <input type="text" v-model="nombre" required />
      </label>
      <label>
        Tipo:
        <input type="text" v-model="tipo" required />
      </label>
      <label>
        Nivel:
        <input type="text" v-model="nivel" required />
      </label>
      <label>
        Descripción:
        <textarea v-model="descripcion" required></textarea>
      </label>
      <label>
        Imagen:
        <input type="file" ref="imagen" required />
      </label>
      <button type="submit" :disabled="loading">Agregar Pokemon</button>
    </form>
  </template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      nombre: "",
      tipo: "",
      nivel: "",
      descripcion: "",
      imagen: null,
      loading: false
    };
  },
  methods: {
    submitForm() {
      if (!this.nombre || !this.tipo || !this.nivel || !this.descripcion || !this.$refs.imagen.files[0]) {
        alert("Por favor, llene todos los campos requeridos.");
        return;
      }
      
      const formData = new FormData();
      formData.append("nombre", this.nombre);
      formData.append("tipo", this.tipo);
      formData.append("nivel", this.nivel);
      formData.append("descripcion", this.descripcion);
      formData.append("imagen", this.$refs.imagen.files[0]);

      this.loading = true;

      axios.post('http://localhost:8087/Pokemon/add', formData)
        .then(response => {
          this.loading = false;
          this.nombre = "";
          this.tipo = "";
          this.nivel = "";
          this.descripcion = "";
          this.$refs.imagen.value = null;
          this.$emit("pokemon-agregado", response.data);
        })
        .catch(error => {
          console.log(error);
          this.loading = false;
        });
    }
  }
};
</script>


<style scoped>
.pokemon-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 500px;
  margin: auto;
  padding: 20px;
  border: 2px solid #ccc;
  border-radius: 10px;
}

.pokemon-form label {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.pokemon-form label span {
  font-weight: bold;
  margin-bottom: 5px;
}

.pokemon-form input[type="text"],
.pokemon-form textarea {
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
}

.pokemon-form textarea {
  height: 100px;
}

.pokemon-form button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
}

.pokemon-form button[type="submit"]:hover {
  background-color: #45a049;
}

@media screen and (max-width: 600px) {
  .pokemon-form {
    max-width: 100%;
    padding: 10px;
  }
}
</style>

