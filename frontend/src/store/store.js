import { createStore } from "vuex";

export default createStore({
    state: {
        user: {},
        token: '',
        login: false,
        movies: [],
        genres: [],
        profile: {}
    },
    getters: {
        user(state) { return state.user },
        token(state) { return state.token },
        isLogin(state) { return state.login },
        movies(state) { return state.movies },
        genres(state) { return state.genres },
        profile(state) { return state.profile },
    },
    mutations: {
        setUser(state, user) { state.user = user },
        setToken(state, token) { state.token = token },
        setLogin(state, login) { state.login = login },
        setMovies(state, movies) { state.movies = movies },
        setGenres(state, genres) { state.genres = genres },
        setProfile(state, profile) { state.profile = profile }
    }
});