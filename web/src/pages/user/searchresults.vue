<style scoped>
  .user {
    width: 100%;
    padding-left: 20px;
    padding-top: 10px;
    border: solid 1px grey;
    border-width: 1px 1px 0 1px;
    height: 100%;
  }
</style>

<template>
  <div>
    <h1>Search Results</h1>
    <div class="col-md-12" v-for="user in users">
      <div class="user">
        <div class="row">
          <div class="col-md-1">
            <img src="http://lorempixel.com/200/200/people/" width="50px" height="50px" alt="">
          </div>
          <div class="col-md-11">
            <router-link :to="{ name: 'Profile',  params: { userId: user.id }}" replace>
              <p>@{{user.username}}</p>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { tabs, tab } from 'vue-strap';

  export default {
    components: { tabs, tab },
    data() {
      return {
        show: false,
        users: '',
        searchTerm: '',
      };
    },
    computed: {
    },
    methods: {
      saveTweet() {
        this.show = false;
      },
      cancelTweet() {
        this.show = false;
      },
      getUsers() {
        this.$http.get('api/user/search/' + this.searchTerm, null, localStorage.getItem('__token')).then((response) => {
          this.users = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
    mounted() {
      this.searchTerm = this.$route.params.q;
      this.getUsers();
    },
  };
</script>
