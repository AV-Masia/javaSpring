<template>
  <div class="movie">
    <div class="movie_img">
      <img
        class="poster-tooltip"
        :onError="handleImageError"
        :src="`${this.path}`"
        width="220"
        height="313"
      />
      <button class="movie_button">Watch online</button>
    </div>
    <div class="movie_body">
      <div class="movie_info"><strong>Id :</strong>{{ movie.id }}</div>
      <div class="movie_info"><strong>Title :</strong>{{ movie.title }}</div>
      <div class="movie_info"><strong>Tagline :</strong>{{ movie.tagline }}</div>
      <div class="movie_info">
        <strong>Vote average :</strong>{{ movie.vote_average }}
      </div>
      <div class="movie_info"><strong>Count average :</strong>{{ movie.vote_count }}</div>
      <div class="movie_info">
        <strong>Release_date average :</strong>{{ movie.release_date }}
      </div>
      <div class="movie_info"><strong>Overview :</strong>{{ movie.overview }}</div>
      <div class="movie_info"><strong>Budget :</strong>{{ movie.budget }}</div>
      <div class="movie_info"><strong>Revenue :</strong>{{ movie.revenue }}</div>
      <div class="movie_info"><strong>Runtime :</strong>{{ movie.runtime }}</div>
      <div class="movie_info" v-for="genre in movie.genres" :key="genre.id">
        <strong>Genre :</strong>{{ genre.name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Movie",
  components: {},
  props: {
    movie: {},
  },
  data() {
    return {
      path:
        this.movie.poster_path != null ? this.movie.poster_path : this.handleImageError(),
    };
  },
  methods: {
    handleImageError: function () {
      this.path = "/assets/logo.png";
    },
  },
};
</script>

<style>
.movie {
  display: flex;
  margin: 0px 0px 30px 0px;
}

.movie_body {
  padding: 0;
  margin: 0;
  border: 0;
  box-sizing: border-box;
  flex: 1 1 auto;
  padding: 0px 0px 0px 30px;
}

.movie_button {
  border: 0;
  margin: 0;
  color: #fff;
  width: 220px;
  height: 50px;
  font-size: 16px;
  cursor: pointer;
  font-weight: 700;
  line-height: 50px;
  text-align: center;
  background: #f26739;
  display: inline-block;
  text-transform: uppercase;
}
.movie_img {
  widows: 220px;
}
</style>
