<template>
  <div>
    <div class="col-md-12">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>username</th>
            <th>role</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users">
            <td>{{user.username}}</td>
            <td>{{user.role}}</td>
            <td><button class="btn btn-primary" @click="ChangeRoleOfUsers(user)">Change role</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        users: '',
      };
    },
    computed: {
    },
    methods: {
      getAllUsers() {
        const token = this.$store.state.auth.accessToken;
        this.$http.get('api/user/', null, token).then((response) => {
          this.users = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
      ChangeRoleOfUsers(user) {
        const userToChange = user;
        if (user.role === 'ADMIN') {
          userToChange.role = 'USER';
        } else {
          userToChange.role = 'ADMIN';
        }
        const token = this.$store.state.auth.accessToken;
        this.$http.put('api/user/', userToChange, token).then(() => {
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
    mounted() {
      this.getAllUsers();
    },
  };

</script>
