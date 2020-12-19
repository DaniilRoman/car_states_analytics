import {carApi, routeApi} from "@/main";
import {CarResponse} from "@/client/admin";


export type CarState = { currentUserCar?: CarResponse };

export default {
    state: {
        currentUserCar: undefined
    } as CarState,
    mutations: {
        replaceCurrentUserCar(state: CarState, cars: Array<CarResponse>) {
            state.currentUserCar = cars[0]
        }
    },
    actions: {
        fetchUserCar(context: any) {
            carApi.getCarsByOwnerId(context.rootState.AccountStore.currentUserId)
                .then(res => context.commit("replaceCurrentUserCar", res.data))
                .catch(er => console.log("Fetch user car fail. \n"+er))
        }
    },
    getters: {
    }
}