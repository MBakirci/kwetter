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
            <button class="btn btn-default" @click="search">{{$lang.search}}</button>
          </form>
          <dropdown :text="username" type="primary">
            <li>
              <router-link :to="{ name: 'Profile', params: { userId }}">Profile</router-link>
            </li>
            <li v-if="isAdmin">
              <router-link :to="{ name: 'Admin'}">Admin Panel</router-link>
            </li>
            <li role="separator" class="divider"></li>
            <li @click.once="logout()">
              <router-link to="/login">Logout</router-link>
            </li>
          </dropdown>
          <ul class="nav navbar-nav navbar-right">
            <dropdown type="primary">
              <button slot="button" type="button" class="btn btn-primary dropdown-toggle">
                Messages ({{messages.length}})
                <span class="caret"></span>
              </button>
              <ul slot="dropdown-menu" class="dropdown-menu">
                <li v-for="m in messages">
                  <router-link :to="{ name: 'Profile',  params: { userId: m.message.user.id }}">
                    <p>{{m.message.user.username}} {{$lang.addedTweet}}</p>
                    <p>"{{m.message.tweet}}"</p>
                    <hr>
                  </router-link>
                </li>
              </ul>
            </dropdown>
            <li>
              <a href="#"></a>
            </li>
            <dropdown text="Taal" type="primary">
              <li v-for="lang in $langs" @click="$setLang(lang)" class="btn btn-danger">{{lang}}</li>
            </dropdown>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view :key="$route.path"></router-view>
    </div>
  </div>
</template>

<script>
  import { dropdown } from 'vue-strap';
  import store from '@/store';

  export default {
    components: { dropdown },
    data() {
      return {
        searchQuery: '',
        websocket: '',
      };
    },
    computed: {
      username() {
        return store.state.user.username;
      },
      messages() {
        return store.state.messages;
      },
      userId() {
        return store.state.user.id;
      },
      isAdmin() {
        if (store.state.user.role === 'ADMIN') {
          return true;
        }
        return false;
      },
    },
    methods: {
      search() {
        this.$router.push({ name: 'Search', params: { q: this.searchQuery } });
      },
      logout() {
        store.commit('CLEAR_ALL_DATA');
        this.$router.push({ name: 'Home' });
      },
      openSocket() {
        this.websocket = new WebSocket('ws://localhost:8080/kwetter/echo-socket/' + this.$store.state.user.id);
        const self = this;
        this.websocket.onmessage = function message(evt) {
          self.$store.commit({
            type: 'UPDATE_MESSAGES',
            message: JSON.parse(evt.data),
          });
        };
      },
    },
    beforeCreate() {
    },
    mounted() {
      this.openSocket();
    },
  };

</script>
