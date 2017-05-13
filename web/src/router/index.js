import Vue from 'vue';
import Router from 'vue-router';
import store from '@/store';

Vue.use(Router);

function checkAuth(to, from, next) {
  const auth = store.state.auth;

  if (!auth.isLoggedIn) {
    next({
      name: 'Login',
      query: { redirect: to.fullPath },
    });
  } else {
    next();
  }
}

const router = new Router({
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
          path: '/admin',
          name: 'Admin',
          component: require('../pages/admin/admin.vue'),
        },
        {
          path: '/user/profile/:userId',
          name: 'Profile',
          component: require('../pages/user/profile.vue'),
        },
        {
          path: '/user/search/:q',
          name: 'Search',
          component: require('../pages/user/searchresults.vue'),
        },
      ],
      beforeEnter: checkAuth,
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

export default router;
