<style>
  .tweet {
    height: 115px;
    width: 100%;
    padding-left: 20px;
    padding-top: 10px;
    background-color: #f5f8fa;
    border: solid 1px grey;
    border-width: 1px 1px 0 1px;
  }
</style>

<template>
  <div>
    <div class="col-md-12">
      <div class="col-md-12">
        <strong>{{user.username}}</strong>
        <br/>
        <img v-if="user" src="http://lorempixel.com/200/200/people/" width="200px" height="200px" alt="">
      </div>
      <div class="col-col-md-12">
        <div class="col-md-2">
          <p class="text-primary">Tweets</p>
          <p class="text-primary">{{myTweets.length}}</p>
        </div>
        <div class="col-md-2">
          <p class="text-primary">Followers</p>
          <p class="text-primary">{{user.followerCount}}</p>
        </div>
        <div class="col-md-2">
          <p class="text-primary">Following</p>
          <p class="text-primary">{{user.followingCount}}</p>
        </div>
        <div class="col-md-2">
          <button class="btn btn-primary" @click="show = true">Add Tweet</button>
        </div>
      </div>
    </div>
    <div class="col-md-12">
      <div class="tweet" v-for="tweet in myTweets">
        <div class="col-md-12">
          <div class="col-md-9">
            <p><strong>{{tweet.user.username}}</strong> {{tweet.user.username}} - {{tweet.timeAgo}}</p>
          </div>
          <div class="col-md-3">
            <i class="fa fa-heart" aria-hidden="true"></i> 0
          </div>
          <div class="col-md-12">
            <p>{{tweet.tweet}}</p>
          </div>
        </div>
      </div>
    </div>
    <modal title="New Tweet" effect="fade/zoom" :value="show" @ok="saveTweet" @cancel="cancelTweet">
      <div class="container-fluid">
        <div class="col-md-10">
          <textarea class="form-control" rows="3" id="textArea" v-model="newTweet" placeholder="Max 140 words"></textarea>
        </div>
      </div>
    </modal>
  </div>
</template>

<script>
  import { modal } from 'vue-strap';

  export default {
    components: { modal },
    data() {
      return {
        show: false,
        newTweet: '',
        myTweets: [],
      };
    },
    computed: {
      user() {
        return this.$store.state.user;
      },
      tweets() {
        return this.$store.state.myTweets;
      },
    },
    methods: {
      saveTweet() {
        const user = this.$store.state.user;
        const tweet = {
          tweet: this.newTweet,
          user,
        };
        this.newTweet = '';
        this.$http.post('api/tweet', tweet).then((response) => {
          this.myTweets.unshift(response.data);
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
        this.show = false;
        this.sendSocket(tweet);
      },
      cancelTweet() {
        this.show = false;
      },
      getMyTweets() {
        const token = this.$store.state.auth.accessToken;
        const userId = this.$store.state.user.id;
        this.$http.get('api/user/' + userId + '/tweets/', null, token).then((response) => {
          this.myTweets = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
      sendSocket(message) {
        const tweet = JSON.stringify(message);
        this.$parent.websocket.send(tweet);
      },
    },
    mounted() {
      this.getMyTweets();
    },
  };

</script>
