<template>
  <div class="card shadow">
    <div class="content">
      <div class="tmp_row">
        <div class="title">{{ route.name }}</div>
<!--        <div></div>-->
        <i class="el-icon-close"></i>
      </div>
      <div class="tmp_row">
        <div class="text">{{ route.date }}</div>
        <div></div>
      </div>
    </div>
    <div class="column">
      <div class="column-item">
<!--        <b-icon class="delete-icon" icon="trash-fill"></b-icon>-->
        Speed: {{getStats(route.route_id)?.avgSpeed}}
      </div>
      <div class="column-item">
        Oil: {{getStats(route.route_id)?.avgOil}}
<!--        <b-icon class="edit-icon" icon="pencil-square"></b-icon>-->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import {mapGetters} from "vuex";

export default defineComponent({
  name: "Route",
  props: ["route"],
  computed: {
    ...mapGetters(["getStats"]),
  }
})
</script>

<style scoped>
.card {
  width: 50%;
  margin: 0 auto;
  height: 10%;

  border: 1px solid #c5c7c9;
  margin-bottom: 1%;
  padding: 10px;
}
.card:hover {
  border-color: #7f8181;
  box-shadow: 0 0 5px 0.2px rgba(0, 0, 0, 0.3);
}
.shadow {
  box-shadow: 0 0 3px 0.2px rgba(46, 46, 46, 0.3);
}
.column {
  text-align: right;
  display: flex;
  flex-direction: column;
}
.column-item {
  margin-bottom: 2%;
}
.content {
  display: flex;
  flex-direction: column;
}
.tmp_row {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.title {
  font-size: 16pt;
}
.text {
  margin-top: 1%;
  color: #a5a7a8;
}
</style>
