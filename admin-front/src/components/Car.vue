<template>
  <div class="card shadow">
    <div class="content">
      <div class="tmp_row">
        <div class="title">{{ car.brand }}</div>
        <div></div>
      </div>
      <div class="tmp_row">
        <div class="text">{{ car.model }}</div>
        <div></div>
      </div>
    </div>
    <div class="row">
      <div class="row-item" v-on:click="deleteNote(notebook.id)">
<!--        <b-icon class="delete-icon" icon="trash-fill"></b-icon>-->
        <div>{{car.owner.username}}</div>
      </div>
<!--      <div class="row-item" v-on:click="editNote">-->
<!--&lt;!&ndash;        <b-icon class="edit-icon" icon="pencil-square"></b-icon>&ndash;&gt;-->
<!--      </div>-->
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: "Car",
  props: ["car"],
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
.row {
  display: flex;
  flex-direction: row-reverse;
  padding-right: 5%;
}
.row-item {
  margin-left: 2%;
}
.content {
  display: flex;
  flex-direction: column;
}
.tmp_row {
  display: flex;
  flex-direction: row;
}
.title {
  font-size: 16pt;
}
.text {
  margin-top: 1%;
  color: #a5a7a8;
}
</style>
