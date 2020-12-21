<template>
  <div class="home">
      <h1>Le go staing</h1>
      <img width="700" alt="Vue logo" src="https://media.giphy.com/media/NN90tlxL6gmsvaPOIt/giphy.gif">

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
export default class Home extends Vue {}
</script>
