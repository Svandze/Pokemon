<template>
  <div id="app">
    <PokemonList />
  </div>
</template>

<script>
import PokemonList from "./components/PokemonList.vue";

export default {
  name: "App",
  components: {
    PokemonList,
  },
};
</script>

<style>
#app {
  font-family: "Helvetica Neue", sans-serif;
  text-align: center;
  color: #2c3e50;
  margin: 0 auto;
  max-width: 100%;
}

</style>
