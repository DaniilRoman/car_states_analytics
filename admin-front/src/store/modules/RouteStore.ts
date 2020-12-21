import {CarRouteResponse, CarRouteStatsResponse} from "@/client/admin";
import {routeApi} from "@/main";

export type RouteState = { test: string; routes: Array<CarRouteResponse>; currentRoute?: CarRouteResponse; routeStatsMap: Map<string, object>};

export default {
    state: {
        test: "Test text 111",
        routes: [],
        currentRoute: undefined,
        routeStatsMap: new Map<string, object>()
    } as RouteState,
    mutations: {
        replaceRoutes(state: RouteState, newRoutes: Array<CarRouteResponse>) {
            state.routes = newRoutes
        },
        replaceStatistics(state: RouteState, stats: Array<CarRouteStatsResponse>) {
            state.routeStatsMap = new Map(stats.map(i => [i.route_id, { avgSpeed: i.avg_speed, avgOil: i.avg_oil }]));
        }
    },
    actions: {
        fetchRoutes(context: any) {
            routeApi.getRoutesByCarId(context.rootState.CarStore.currentUserCar.id)
                .then(res => {
                    context.commit("replaceRoutes", res.data)
                    context.dispatch("fetchRouteStatistics")
                })
                .catch(ex => console.log("Error while fetch routes. \n"+ex))
        },
        fetchRouteStatistics(context: any) {
            routeApi.getRoutesStats(context.rootState.CarStore.currentUserCar.id, context.rootState.AccountStore.currentUserId)
                .then(res => context.commit("replaceStatistics", res.data))
                .catch(ex => console.log("Error while fetch routes. \n"+ex))
        }
    },
    getters: {
        getRoutes(state: RouteState) {
            return state.routes
        },
        getStats(state: RouteState) {
            return (routeId: string) => state.routeStatsMap.get(routeId)
        }
    }
}