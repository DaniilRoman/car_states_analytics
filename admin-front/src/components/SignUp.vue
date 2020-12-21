<template>
  <div class="login">
    <div class="login-form">
      <div title="Login" style="max-width: 20rem;" class="mb-2">
        <div>
          <el-input class="custom_input" v-model="username" />
          <div class="mt-2"></div>

          <el-input class="custom_input" v-model="password" show-password/>
          <div class="mt-2"></div>
        </div>

        <el-button @click="_signUp(username, password)" >SignUp</el-button>
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
.login-form {
  margin-left: 38%;
  margin-top: 50px;
}
.login-btn {
  margin-top: 10px;
}
.custom_input {
  margin-bottom: 5px;
}
</style>