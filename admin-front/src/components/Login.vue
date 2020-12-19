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

        <button v-on:click="_login" >Login</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {useStore} from "vuex";

export default defineComponent({
  name: "Login",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    _login() {
      const store = useStore()
      store.dispatch("login", { username: this.username, password: this.password })
    }
  }
})
</script>

<style scoped>
</style>