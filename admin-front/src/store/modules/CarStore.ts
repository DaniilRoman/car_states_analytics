import {carApi, routeApi} from "@/main";
import {CarResponse} from "@/client/admin";


export type CarState = { currentUserCar?: CarResponse; cars: Array<CarResponse> };

export default {
    state: {
        currentUserCar: undefined,
        cars: []
    } as CarState,
    mutations: {
        replaceCurrentUserCar(state: CarState, cars: Array<CarResponse>) {
            state.currentUserCar = cars[0]
        },
        replaceCars(state: CarState, cars: Array<CarResponse>) {
            state.cars = cars
        }
    },
    actions: {
        fetchUserCar(context: any) {
            const promise = carApi.getCarsByOwnerId(context.rootState.AccountStore.currentUserId)
            promise.then(res => {
                context.commit("replaceCurrentUserCar", res.data)
            })
            .catch(er => console.log("Fetch user car fail. \n"+er))
            return promise
        },
        fetchCars(context: any) {
            const promise = carApi.getCars()
            promise.then(res => {
                context.commit("replaceCars", res.data)
            }).catch(er => console.log("Fetch cars fail. \n"+er))
            return promise
        }
    },
    getters: {
    }
}