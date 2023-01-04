import { createApp, ref, onMounted } from 'vue'

import App from './App.vue'

createApp(App).mount('#app')

//
//const app = createApp({})
//
//app.component( 'messages-list',{
//    props: ['messages'],
//    template: '<div><div v-form="message in messages"> {{ message.text}}</div></div>'
//  });

//createApp({
//        el: '#app',
//        data() {
//            message: 'привет Vue'
//        }
//});

//createApp({
//    data() {
//        return {
//            messages: [
//                {id: '123', text: 'Wow'},
//                {id: '23', text: 'More'},
//                {id: '3', text: 'Another'},
//            ]
//        }
//    },
//    template: `<message-list :message="message" />`
//}).mount('#app')


//  createApp({
//    data() {
//        return {
//            count: 0,
//            msg: "!!!!!!!!=",
//            messages: [
//                {id: '123', text: 'Wow'},
//                {id: '23', text: 'More'},
//                {id: '3', text: 'Another'},
//            ]
//        }
//    },
//    methods: {
//        increment() {
//          this.count++
//        },
//                increment2() {
//                  this.msg = msg + count
//                }
//    },
//    mounted() {
//        console.log(`The initial count is ${this.count}.`)
//    },
//    components: {
//
//    },
//    template: `
//        <button @click="increment">Count is: {{ count }}</button>
//        <span>Message: {{ msg }}</span>
//        <br>
//        <button @click="increment2">change msg</button>
//        <br>
//        <messages-list :messages="messages" />
//    `
//}).mount('#app')
