import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: require('../components/main.vue'),
      children: [
        {
          path: '/',
          name: 'Home',
          component: require('../pages/user/home.vue'),
        },
        {
          path: '/user/profile',
          name: 'Profile',
          component: require('../pages/user/profile.vue'),
        },
        {
          path: '/user/search',
          name: 'SearchResults',
          component: require('../pages/user/searchresults.vue'),
        },
      ],
    },
    {
      path: '/login',
      name: 'Login',
      component: require('../pages/login.vue'),
    },
    {
      path: '/register',
      name: 'Register',
      component: require('../pages/register.vue'),
    },
    {
      path: '/error',
      name: 'Error',
      component: require('../pages/error.vue'),
    },
  ],
});
