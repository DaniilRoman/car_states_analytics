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

        <button v-on:click="_login(username, password)" >Login</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {useStore} from "vuex";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  setup() {
    const store = useStore()

    const _login = (username: any, password: any) => {
      store.dispatch("login", { username: username, password: password })
    }
    return { _login }
  }
}
</script>

<style scoped>
</style>