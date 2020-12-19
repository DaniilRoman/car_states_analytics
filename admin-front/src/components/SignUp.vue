<template>
  <div class="home">
    <b-jumbotron>

      <img alt="Vue logo" src="../assets/logo.png">
      <h1>Home</h1>
      <!--    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->

    </b-jumbotron>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src

@Options({
  components: {
    HelloWorld,
  },
})
export default class SignUp extends Vue {}
</script>
