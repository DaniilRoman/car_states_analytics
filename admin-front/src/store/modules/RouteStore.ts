import {CarRouteResponse} from "@/client/admin";
import {routeApi} from "@/main";

export type RouteState = { test: string; routes: Array<CarRouteResponse>; currentRoute?: CarRouteResponse};

export default {
    state: {
        test: "Test text 111",
        routes: [],
        currentRoute: undefined
    } as RouteState,
    mutations: {
        replaceRoutes(state: RouteState, newRoutes: Array<CarRouteResponse>) {
            state.routes = newRoutes
        }
    },
    actions: {
        fetchRoutes(context: any) {
            routeApi.getRoutesByCarId(context.rootState.CarStore.currentUserCar.id)
            // new Promise<Array<CarRouteResponse>>((resolve, reject)=>{
            //     eslint-disable-next-line @typescript-eslint/camelcase
            //     resolve([{"route_id": "1"}, {"route_id": "2"}])
            // })
            //     .then(res => context.commit("replaceRoutes", res))
                .then(res => context.commit("replaceRoutes", res.data))
                .catch(ex => console.log("Error while fetch routes. \n"+ex))
        }
    },
    getters: {
        getText(state: RouteState, getters: any, rootState: any, rootGetters: any) {
            // return rootGetters.isAuthenticated
            return state.test
        },
        getRoutes(state: RouteState) {
            return state.routes
        }
    }
}