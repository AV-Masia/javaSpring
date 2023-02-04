<template>
  <div class="app">
    <h1>Страница с постами</h1>
    <my-input
      v-model=searchQuery
      placeholder="Поиск..."
    />
    <div class="app_btns">
      <my-button
        @click=showDialog
      >
      Создать пользователя
      </my-button>
      <my-select
        v-model="selectedSort"
        :options="sortOptions"
      />
    </div>
    

    <my-dialog v-model:show="dialogVisible">
      <post-Form 
      @create=createPost
      />
    </my-dialog>
    <post-List 
      :posts=sortedEndSearchPosts
      @remove=removePost
      v-if=!isPostLoading
    />
    <div v-else>Идет загрузка...</div>
    <!-- <div ref="observer" class="observer"></div> -->
  
    <div class="page__wrapper">
      <div 
        v-for="pageNumber in totalPages" 
        :key="pageNumber"
        class="page"
        :class="{
          'current-page': page === pageNumber
        }"
        @click=changePage(pageNumber)
      >
        {{pageNumber}}
      </div>
    </div>

  </div>
</template>

<script>
// import { ref } from 'vue';
import PostForm from "@/components/PostForm";
import PostList from "@/components/PostList";
import MyButton from "@/components/Ui/MyButton";
import MyInput from "@/components/Ui/MyInput";
import MyDialog from "@/components/Ui/MyDialog";
import MySelect from "@/components/Ui/MySelect";

import axios from 'axios';
  export default {
    components: {
      PostForm, PostList, MyButton, MyInput, MyDialog, MySelect
    },
    data() {
      return {
        page: 1,
        posts: [],
        dialogVisible: false,
        isPostLoading: false,
        selectedSort: '',
        searchQuery: '',
        pageNumber: 1,
        limit: 10,
        totalPages: 10,
        sortOptions: [
          {value: 'title', name: 'По названию'},
          {value: 'body', name: 'По содержимому'}
        ],
      }
    },
    methods: {
      createPost: function(post) {
        this.posts.push(post);
        this.dialogVisible = false;
      },
      removePost: function(post) {
        this.posts = this.posts.filter(p => p.id !== post.id)
      },
      showDialog: function() {
        this.dialogVisible = true;
      },
      changePage: function(pageNumber) {
        this.page = pageNumber;
        this.fetchPosts();
      },
      fetchPosts: async function() {
        await axios
      .get('https://jsonplaceholder.typicode.com/posts',
              {
                params: {
                  _page: this.page,
                  _limit: this.limit
                }
              }
            )
      .then(response => {
        this.totalPages = Math.ceil(response.headers['x-total-count'] / this.limit);
        this.posts = response.data;
      })
      .catch(error => {
        console.log(error)
        alert('Ошибка')
      })
      .finally(() => this.isPostLoading = false)
    },
    loadMorePosts: async function() {
        await axios
      .get('https://jsonplaceholder.typicode.com/posts',
              {
                params: {
                  _page: this.page,
                  _limit: this.limit
                }
              }
            )
      .then(response => {
        this.totalPages = Math.ceil(response.headers['x-total-count'] / this.limit);
        this.posts = [...this.posts, ...response.data];
      })
      .catch(error => {
        console.log(error)
        alert('Ошибка')
      })
      .finally(() => this.isPostLoading = false)
    }
    },
    mounted() {
      this.fetchPosts();
      // console.log(this.$refs.observer);
      // const options = {
      //   rootMargin: '0px',
      //   threshold: 1.0
      // }
      // const callback = function(entries, observer) {

      // };
      // const observer = new IntersectionObserver(callback, options);
    },
    computed: {
      sortedPosts() {
        return [...this.posts].sort((post1, post2) => 
        post1[this.selectedSort]?.localeCompare(post2[this.selectedSort]))
      },
      sortedEndSearchPosts: function() {
        return this.sortedPosts.filter(post => post.title.toLowerCase()
        .includes(this.searchQuery.toLowerCase()))
      }
    },
    watch: {
      // page() {
      //   this.fetchPosts()
      // }
    }
  }

</script>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  .app {
    padding: 20px;
  }

  .page__wrapper {
    display: flex;
    margin-top: 15px;
  }

  .page {
    border: 1px solid black;
    padding: 10px;
  }

  .current-page {
    border: 2px solid teal;
  }

  .app_btns {
    margin: 15px 0px;
    display: flex;
    justify-content: space-between;
  }

  .observer {
    height: 30px;
    background: green;
  }
</style>
