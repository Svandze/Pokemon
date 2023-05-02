<template>
  <div class="pokemon-list">
    <h1 class="pokemon-list-title">Pokedex</h1>
    <pokemon-form @pokemon-agregado="agregarPokemon" />
    <div class="pokemon-cards">
      <pokemon-card v-for="pokemon in pokemons" :key="pokemon.id" :pokemon="pokemon" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import PokemonCard from './PokemonCard.vue';
import PokemonForm from './PokemonForm.vue';

export default {
  components: {
    PokemonCard,
    PokemonForm
  },
  data() {
    return {
      pokemons: [],
      loading: false
    };
  },
  methods: {
    agregarPokemon(pokemon) {
      this.pokemons.push(pokemon);
    }
  },
  created() {
    axios.get('http://localhost:8087/Pokemon/all')
      .then(response => {
        this.pokemons = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>

<style scoped>
.pokemon-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
  background-color: black;
}

.pokemon-list-title {
  font-size: 32px;
  color: #f44336;
  text-align: center;
  margin-bottom: 20px;
}
body {
  margin: 0;
  padding: 0;
  background-color: black;
}

.pokemon-form {
  margin: 0 auto;
  max-width: 500px;
  width: 100%;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
}

.pokemon-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  grid-gap: 20px;
  justify-content: center;
}

@media screen and (max-width: 768px) {
  .pokemon-cards {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
}

@media screen and (max-width: 480px) {
  .pokemon-cards {
    grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  }
}
</style>
