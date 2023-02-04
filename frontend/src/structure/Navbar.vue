<template>
  <div id="navbar">
    <div id="frame">
      <div class="frame">
        <div id="menu">
          <ul>
            <li class="level">
              <span class="s1">
                <a class="level" v-on:click="getAllmovies()">
                  All movies
                </a>
              </span>
              <span class="brace">|</span>
            </li>
            <li class="genres" v-for="genre in this.genres" :key="genre.id">
              <span class="s1 genre" v-on:click="getMoviesByGenre(genre.name)">
                <a class="level">
                  {{ genre.name }}
                </a>
              </span>
              <span class="brace">|</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

export default {
  name: "Navbar",
  components: {},
  computed: {
    genres: function () {
      return this.$store.state.genres;
    },
  },
  created() {
    this.getGenres();
    this.getAllmovies();
  },
  methods: {
    async getAllmovies() {
      await axios
        .get("/api/get_all_movies")
        .then((result) => {
          this.$store.commit("setMovies", result.data);
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
    async getMoviesByGenre(genre) {
      await axios({
        method: "get",
        url: "/api/filter_movies_by_genre",
        params: {
          genre: genre,
        },
      })
        .then((result) => {
          this.$store.commit("setMovies", result.data);
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
    async getGenres() {
      await axios
        .get("/api/get_all_genres")
        .then((result) => {
          this.$store.commit("setGenres", result.data);
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
  },
};
</script>

<style>
#navbar #frame {
  width: 990px;
  margin: 0 auto;
  margin-top: 5px;
}

#navbar .frame {
  width: 990px;
  background: #fff;
  margin: 0;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  z-index: 9999;
}

#navbar #menu {
  position: relative;
  width: 990px;
  height: 44px;
  background: #d96142;
  border-top: 1px solid #d96142;
  display: block;
  border-bottom: 1px solid #fff;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 0 0 3px 3px;
  font-size: 14px;
  z-index: 500;
}

#navbar #menu ul {
  width: 100%;
  text-align: left;
  display: inline;
  list-style: none;
  padding: 0;
  margin: 1px;
  z-index: 900;
}

#navbar #menu ul li.level {
  margin-right: -10px;
  font-weight: 700;
}

#navbar #menu ul li {
  width: auto;
  display: inline-block;
  padding: 12px 10px;
  color: #fff;
  height: 20px;
}

#navbar #menu ul li a.level {
  color: #fff;
}

#navbar #menu ul li a:hover {
  padding-bottom: 2px;
  box-shadow: inset 0 -2px 0 0 #333;
}

#navbar #menu ul .brace {
  font-weight: 400;
  width: 1px;
  height: 20px;
  color: #fff;
  font-size: 22px;
  padding-left: 5px;
}
</style>
