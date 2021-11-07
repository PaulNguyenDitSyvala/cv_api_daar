<template>
  <h1>CV search indexed using ElasticSearch aaa</h1>
  <post-cv/>
  <search-cv/>
</template>

<script>

import PostCv from './components/PostCv.vue'
import SearchCv from './components/SearchCv.vue'

export default {
  name: 'App',
  components: {
    PostCv,
    SearchCv
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
