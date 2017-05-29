// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import lang from 'vue-lang';
import App from './App';
import router from './router';
import request from './utils/request';
import store from './store';

const locales = {
  en: require('./locale/en.json'),
  nl: require('./locale/nl.json'),
};

Vue.use(lang, { lang: 'en', locales });

const websocket = '';
const messages = '';

Vue.prototype.$http = request;
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  websocket,
  messages,
  lang,
  template: '<App/>',
  components: { App },
});
