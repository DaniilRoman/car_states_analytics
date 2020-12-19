import AccountStore from "@/store/modules/AccountStore";
import RouteStore from "@/store/modules/RouteStore";
import CarStore from "@/store/modules/CarStore";

import { createStore } from 'vuex'

export default createStore({
  modules: {
    AccountStore,
    RouteStore,
    CarStore
  }
})