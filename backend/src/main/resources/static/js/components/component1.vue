<script>
export default {
  data() {
    return {
      todos: [
            { text: 'Изучить JavaScript' },
            { text: 'Изучить Vue' },
            { text: 'Создать что-нибудь классное' }
          ]
    }
  }
}
</script>

<template>
  <ol>
      <li v-for="todo in todos">
        {{ todo.text }}
      </li>
    </ol>
</template>