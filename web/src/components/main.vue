<style>
  /* wrapper */
  
  .has-dropdown input[type="checkbox"] {
    display: none;
  }
  
  .has-dropdown input[type="checkbox"]:checked ~ .dropdown {
    display: block;
  }
  /* dropdown basic */
  
  .dropdown {
    display: none;
    position: absolute;
    z-index: 9999;
  }
  /* dropdown optional styling */
  
  .dropdown li:hover {
    background-color: #eee;
    cursor: pointer;
  }
</style>

<template>
  <div>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">Kwetter</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" v-model="searchQuery" class="form-control" placeholder="Search">
            </div>
            <button class="btn btn-default" @click="search">Submit</button>
          </form>
          <dropdown text="Action" type="primary">
            <li>
              <router-link to="/user/profile">Profile</router-link>
            </li>
            <li role="separator" class="divider"></li>
            <li>
              <router-link to="/login">Logout</router-link>
            </li>
          </dropdown>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import { dropdown } from 'vue-strap';

  export default {
    components: { dropdown },
    data() {
      return {
        searchQuery: '',
      };
    },
    computed: {
    },
    methods: {
      search() {
        this.$router.push({ path: '/user/search', query: { q: this.searchQuery } });
      },
    },
  };

</script>
