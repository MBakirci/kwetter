import vue from 'vue';
import Vuex from 'vuex';

vue.use(Vuex);

const store = new Vuex.Store({
  root: process.env.API_URL,
  searchValue: '',
  modules: {},
});

export default store;
