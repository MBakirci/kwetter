/*eslint-disable*/

import vue from 'vue';
import Vuex from 'vuex';

vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    user: '',
    tweets: '',
  },
  mutations: {
    login(state, currentUser) {
      state.user = currentUser;
    },
    tweets(state, mytweets) {
      state.tweets = mytweets;
    }
  },
});

export default store;
/*eslint-enable*/
