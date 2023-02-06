<template>
  <div class="list_movies">
    <Movie :movie="movie" v-for="movie in this.movies" :key="movie.id"></Movie>
  </div>
</template>

<script>
import Movie from "@/components/Movie";

export default {
  name: "ListMovies",
  components: { Movie },
  computed: {
    movies: function () {
      return this.$store.state.movies;
    },
  },
};
</script>

<style>
.list_movies {
  padding: 50px 15px;
  width: 970px;
}
</style>
