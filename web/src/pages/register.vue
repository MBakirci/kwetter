<template>
  <div>
    <h1>KWETTER</h1>
    <div class="panel panel-primary col-md-6 col-md-offset-3">
      <div class="panel-heading">
        <h3 class="panel-title">Register</h3>
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
                <input type="password" class="form-control" id="inputPassword" v-model="credentials.password" placeholder="Password">
                <div class="checkbox">
                </div>
              </div>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" @click="register()">Register</button>
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
      register() {
        this.$http.post('api/user', this.credentials).then(() => {
          this.$router.push({ name: 'Login' });
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
  };

</script>
