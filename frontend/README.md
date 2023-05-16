# frontend

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

    beforeCreate(){
        console.log('beforeCreate()');
    },
    created(){
        console.log('created()');
    },
    beforeMount(){
        console.log('beforeMount()');
    },
    mounted(){
        console.log('mounted()');
    },
    beforeUpdate(){
        console.log('beforeUpdate()');
    },
    updated(){
        console.log('updated()');
    },
    beforeUnmount(){
        console.log('beforeUnmount()');
    },
    unmounted(){
        console.log('unmounted()');
    }

      :src="`${movie.poster_path}`"
      
   ### The project used a database from this resource
   https://github.com/VarvaraZadnepriak/MoviesAPI.ReactJS.git
