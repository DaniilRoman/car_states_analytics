import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import { TestApi } from './client/admin';
import axios from 'axios'

createApp(App).use(store).use(router).mount('#app')


const BASE_PATH = "http://localhost:9090"

export const AXIOS = axios.create({baseURL: BASE_PATH})

export const testApi = new TestApi({}, BASE_PATH, AXIOS)
