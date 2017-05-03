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
                <input type="text" class="form-control" v-model="credentials.username" id="usernameInput" name="j_username" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-lg-2 control-label">Password</label>
              <div class="col-lg-10">
                <input type="password" class="form-control" v-model="credentials.password" id="inputPassword" name="j_password" placeholder="Password">
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
        const formData = new FormData();
        formData.append('foo', 'bar');
        this.$http.post('j_security_check', formData).then((response) => {
          console.log(response);
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
    mounted: {
    },
  };

</script>
