<template>
  <div class="routes">
    <div class="routes">
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
import {defineComponent} from 'vue'
import {useStore, mapGetters, mapState} from "vuex";
import Route from "@/components/Route.vue";

export default defineComponent({
  components: {
    Route
  },

  computed: {
    ...mapState({
      routes: (state: any) => state.RouteStore.routes
    })
  },

  mounted() {
    const store = useStore()
    store.dispatch("fetchRoutes")
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