<template>
  <div id="app">
    <b-navbar style="width: 100%" type="dark" variant="dark">
      <b-navbar-brand id="nav-brand" ></b-navbar-brand>
      <router-link to="/">         <img height="30px" src="./assets/notebook-logo.png" alt="Notebook" v-if="!isAuthenticated"/>Home</router-link> |
      <router-link to="/routes"><img height="30px" src="./assets/notebook-logo.png" alt="Notebook" v-if="isAuthenticated" />Routes</router-link> |
      <router-link to="/admin" class="nav-link text-light" v-if="isAuthenticated && isAdmin">Admin</router-link> |
      <router-link to="/signup" class="nav-link text-light" v-if="!isAuthenticated">SignUp</router-link> |
      <router-link to="/login" class="nav-link text-light" v-if="!isAuthenticated">Login</router-link> |
      <a href="/" class="nav-link text-light" v-if="isAuthenticated" v-on:click="logout">Logout</a>
    </b-navbar>
    <router-view/>
  </div>
</template>

<script lang="ts">
import { mapGetters, mapActions } from "vuex";
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'App',
  // computed: { ...mapGetters(["isAuthenticated", "isAdmin"]) },
  // methods: { ...mapActions(["logout"]) }

  setup () {
    return { isAuthenticated: false, isAdmin: true, logout: () => { console.log("Logout") }}
  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
