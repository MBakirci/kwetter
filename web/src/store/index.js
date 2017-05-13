// import vue from 'vue';
// import Vuex from 'vuex';

// vue.use(Vuex);

// const store = new Vuex.Store({
//   state: {
//     user: '',
//     tweets: '',
//   },
//   mutations: {
//     login(state, currentUser) {
//       state.user = currentUser;
//     },
//     tweets(state, mytweets) {
//       state.tweets = mytweets;
//     }
//   },
// });
import Vue from 'vue';
import Vuex from 'vuex';
import { state } from './state';
import * as getters from './getters';
import * as actions from './actions';
import * as mutations from './mutations';
import plugins from './plugins';

Vue.use(Vuex);

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  plugins,
});

export default store;
