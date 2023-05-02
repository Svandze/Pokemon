<template>
    <div class="pokemon-card">
      <h3>{{ pokemon.nombre }}</h3>
      <img :src="pokemon.imagenUrl" :alt="'imagen de ' + pokemon.nombre" />
      <div class="pokemon-details">
        <p><strong>Tipo:</strong> {{ pokemon.tipo }}</p>
        <p><strong>Nivel:</strong> {{ pokemon.nivel }}</p>
        <p><strong>Descripción:</strong> {{ pokemon.descripcion }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      pokemon: {
        type: Object,
        required: true
      }
    }
  }
  </script>
  
  <style scoped>
  .pokemon-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    padding: 20px;
    margin: 10px;
    text-align: center;
  }
  
  .pokemon-card h3 {
    margin-top: 0;
  }
  
  .pokemon-card img {
    max-width: 100%;
    height: auto;
    margin-bottom: 10px;
  }
  
  .pokemon-card .pokemon-details {
    margin-top: 10px;
  }
  </style>
  