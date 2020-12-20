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

        <button @click="_signUp(username, password)" >SignUp</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {useStore} from "vuex";

  export default {
  name: "SignUp",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  setup() {
    const store = useStore()

    const check = (username: any, password: any) => {
      console.log(username + ": :" + password)
    }

    const _signUp = (username: any, password: any) => {
      store.dispatch("signUp", { username: username, password: password })
    }
    return { _signUp, check }
  }
}
</script>

<style scoped>
</style>