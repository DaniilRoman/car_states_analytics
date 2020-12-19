<template>
  <div class="login">
    <div class="login-form">
      <div title="Login" style="max-width: 20rem;" class="mb-2">
        <div>
          <input v-model="username" />
          <div class="mt-2"></div>

          <input v-model="password" />
          <div class="mt-2"></div>
        </div>

        <button v-on:click="_signUp" >SignUp</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {useStore} from "vuex";

export default defineComponent({
  name: "SignUp",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    _signUp() {
      const store = useStore()
      store.dispatch("signUp", { username: this.username, password: this.password })
    }
  }
})
</script>

<style scoped>
</style>