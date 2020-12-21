<template>
  <div class="routes">
    <div class="routes">
      <div class="cars" v-for="car in cars" :key="car.id">
        <Car :car="car"/>
      </div>
    </div>
  </div>

  <div>
<!--    <icon v-b-modal.create-note-modal class="add-icon" icon="plus-square">-->
<!--    </icon>-->
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {useStore, mapState} from "vuex";
import Car from "@/components/Car.vue";

export default defineComponent({
  components: {
    Car
  },

  computed: {
    ...mapState({
      cars: (state: any) => state.CarStore.cars
    })
  },

  mounted() {
    const store = useStore()
    store.dispatch("fetchCars")
  },

  setup() {
    return {}
  },
  name: "Cars"
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