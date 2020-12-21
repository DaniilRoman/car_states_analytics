import {AccountRequest, TokenResponse} from "@/client/admin";
import {accountApi} from "@/main";
import router from "@/router";


export type AccountState = { token: string; currentUserId: string; role: string; username: string };

export default {
    state: {
        token: "",
        currentUserId: "",
        role: "",
        username: ""
    } as AccountState,
    mutations: {
        login(state: AccountState, tokenRes: TokenResponse) {
            state.currentUserId = tokenRes.accountId
            state.token = "Bearer "+tokenRes.token
            state.username = tokenRes.username
            const adminRole = tokenRes.authorities.find(auth => auth === 'ROLE_ADMIN')
            if (adminRole == undefined) {
                state.role = 'ROLE_USER'
            } else {
                state.role = 'ROLE_ADMIN'
            }
        },
        logout(state: AccountState) {
            state.currentUserId = ""
            state.token = ""
            state.username = ""
            state.role = ""
        }
    },
    actions: {
        signUp(context: any, user: AccountRequest) {
            accountApi.singUp(user)
                .then(res => context.dispatch("login", user))
                .catch(ex => console.log("Sign up failed. \n"+ex))
        },

        login(context: any, user: AccountRequest) {
            accountApi.login(user)
                .then(res => {
                    context.commit("login", res.data)
                    if (!context.getters.isAdmin) {
                        context.dispatch("fetchUserCar").then(() => router.push("/routes"))
                    } else {
                        context.dispatch("fetchCars").then(() => router.push("/cars"))
                    }
                })
                .catch(ex => console.log("Login failed. \n"+ex))
        }
    },
    getters: {
        isAuthenticated(state: AccountState) {
            return state.token != ""
        },
        isAdmin(state: AccountState) {
            return state.role == "ROLE_ADMIN"
        },
        getToken(state: AccountState) {
            return state.token
        }
    }
}