<template>
  <div>
    <h1>KWETTER {{store.root}}</h1>
    <div class="panel panel-primary col-md-6 col-md-offset-3">
      <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" @submit.prevent>
          <fieldset>
            <div class="form-group">
              <label for="usernameInput" class="col-lg-2 control-label">Username</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" v-model="credentials.username" id="usernameInput" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-lg-2 control-label">Password</label>
              <div class="col-lg-10">
                <input type="password" class="form-control" v-model="credentials.password" id="inputPassword" placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <router-link to="/register">
                <p>Not an account? Register here.</p>
              </router-link>
              <button @click="login" class="btn btn-primary">Login</button>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import { modal } from 'vue-strap';
  import store from '@/store';

  export default {
    components: { modal },
    data() {
      return {
        store,
        show: false,
        newTweet: '',
        credentials: {
          username: '',
          password: '',
        },
        user: {},
      };
    },
    computed: {
    },
    methods: {
      login() {
        this.$http.post('api/auth', this.credentials).then((response) => {
          const auth = store.state.auth;
          // Auth
          auth.isLoggedIn = true;
          auth.accessToken = response.data.token;
          store.commit('UPDATE_AUTH', auth);
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
        this.currentUser();
      },
      currentUser() {
        const token = store.state.auth.accessToken;
        this.$http.get('api/user/currentuser', null, token).then((response) => {
          this.$store.commit('UPDATE_USER', response.data);
          this.$router.push({ name: 'Home' });
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
  };

</script>
