<template>
  
  <!-- <div id="app"> -->
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js + TypeScript App (Project by Alan)"/> -->
  <!-- </div> -->

  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark" stlye="background-color: #039B35;">
      <a class="navbar-brand" href="#">
        <img alt="Vue logo" src="./assets/logo.png" width="100px">
      </a>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" to="/" exact>Welcome</router-link>
          </li>
          <!-- <li class="nav-item">
            <router-link class="nav-link" to="/sprint0" exact>Sprint 0</router-link>
          </li> -->
          <li class="nav-item">
            <router-link class="nav-link" to="/user" exact>User page</router-link>
          </li>
          <!-- <li class="nav-item">
            <router-link class="nav-link" to="/sprint2" exact>Sprint 2</router-link>
          </li> -->
        </ul>
      </div>
    </nav>
    <div class="container mt-3">
      <router-view class="mt-5" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import HelloWorld from './components/HelloWorld.vue';

@Component({
  components: {
    HelloWorld,
  },
})
export default class App extends Vue {}
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
