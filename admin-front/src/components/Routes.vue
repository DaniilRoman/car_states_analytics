<template>
  <div class="routes">
    <div class="routes">
      <div>ROUTE</div>
      <div>{{ getText }}</div>
      <div>{{ test }}</div>
      <div class="routes" v-for="route in routes" :key="route.route_id">
        <Route :route="route"/>
      </div>
    </div>
  </div>

  <div>
    <!--      <b-icon v-b-modal.create-note-modal class="add-icon" icon="plus-square">-->
    <!--      </b-icon>-->
  </div>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue'
import {useStore, mapGetters, mapState, mapActions} from "vuex";
import Route from "@/components/Route.vue";

export default defineComponent({
  components: {
    Route
  },

  computed: {
    ...mapGetters(["getText"]),
    ...mapState({
      test: (state: any) => state.RouteStore.test,
      routes: (state: any) => state.RouteStore.routes
    })
  },

  mounted() {
    const store = useStore()
    store.dispatch("signUp", { username: 115, password: 112 })
    setTimeout(() => {
      setTimeout(() => store.dispatch("fetchUserCar"), 1000)
      store.dispatch("fetchRoutes")
    }, 1000)
  },

  setup() {
    // const store = useStore()
    // const routes: Array<CarRouteResponse> = ref(store.state.routes)

    return {}
  },
  name: "Routes"
})
</script>

<style scoped>
.routes {
  margin-top: 2%;
}

.add-icon {
  width: 30px;
  height: 30px;
  color: #c5c7c9;
  box-shadow: 0 0 5px -1px rgba(0, 0, 0, 0.2);
}

.add-icon:active {
  color: #a5a7a8;
  box-shadow: 0 0 5px -1px rgba(0, 0, 0, 0.6);
}

.add-icon:hover {
  color: #a5a7a8;
}
</style>