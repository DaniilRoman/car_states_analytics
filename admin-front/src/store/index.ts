import AccountStore from "@/store/modules/AccountStore";
import RouteStore from "@/store/modules/RouteStore";

import { createStore } from 'vuex'

export default createStore({
  modules: {
    AccountStore,
    RouteStore
  }
})