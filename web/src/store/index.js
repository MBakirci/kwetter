import vue from 'vue';
import Vuex from 'vuex';

vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    user: '',
  },
  mutations: {
    login(loggeduser) {
      this.state.user = loggeduser;
    },
  },
});

export default store;
