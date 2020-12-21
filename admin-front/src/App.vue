<template>
  <div id="app">
    <el-menu class="el-menu-demo" mode="horizontal">
      <el-menu-item index="1" v-if="!isAuthenticated">
        <router-link to="/">
          <img height="30px" src="./assets/notebook-logo.png" alt="Notebook"/>Home
        </router-link>
      </el-menu-item>
      <el-menu-item index="2" v-if="isAuthenticated && !isAdmin" >
        <router-link to="/routes">Routes</router-link>
      </el-menu-item>
      <el-menu-item index="3" v-if="isAuthenticated && isAdmin">
        <router-link to="/cars">Cars</router-link>
      </el-menu-item>
      <el-menu-item index="4" v-if="!isAuthenticated">
        <router-link to="/signup" >SignUp</router-link>
      </el-menu-item>
      <el-menu-item index="5" v-if="!isAuthenticated">
        <router-link to="/login" >Login</router-link>
      </el-menu-item>
      <el-menu-item index="6" v-if="isAuthenticated">
        <router-link to="/" v-on:click="logout">Logout</router-link>
      </el-menu-item>
    </el-menu>


    <router-view/>
  </div>
</template>

<script lang="ts">
import {mapGetters, mapMutations} from "vuex";
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'App',
  computed: { ...mapGetters(["isAuthenticated", "isAdmin"]) },
  methods: { ...mapMutations(["logout"]) }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c5680;
}
</style>
