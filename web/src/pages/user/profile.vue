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
        <img src="http://lorempixel.com/200/200/people/" width="200px" height="200px" alt="">
      </div>
      <div class="col-col-md-12">
        <tabs v-model="activeTab" nav-style="tabs" justified>
          <tab header="Tweets">
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
          </tab>
          <tab header="Following">
            <div class="col-md-12">
              <div class="col-md-4" style="border: solid 1px grey;" v-for="follow in Following">
                <router-link :to="{ name: 'Profile',  params: { userId: follow.id }}" replace>
                  <div class="col-md-12">
                    <img src="http://lorempixel.com/200/200/people/" width="200px" height="200px" alt="">
                    <div class="col-md-12">
                      <p><strong>@{{follow.username}}</strong></p>
                    </div>
                    <div class="col-md-12">
                      <p>{{follow.username}}</p>
                    </div>
                    <div class="col-md-12" <div class="col-md-4">
                      <p class="text-primary">Tweets <br/>{{follow.tweetCount}}</p>
                    </div>
                    <div class="col-md-4">
                      <p class="text-primary">Followers <br/>{{follow.followerCount}}</p>
                    </div>
                    <div class="col-md-4">
                      <p class="text-primary">Following <br/>{{follow.followingCount}}</p>
                    </div>
                  </div>
                  <hr/>
                </router-link>
              </div>
            </div>
          </tab>
          <tab header="Followers">
            <div class="col-md-12">
              <div class="col-md-4" style="border: solid 1px grey;" v-for="follower in Followers">
                <router-link :to="{ name: 'Profile',  params: { userId: follower.id }}" replace>
                  <div class="col-md-12">
                    <img src="http://lorempixel.com/200/200/people/" width="200px" height="200px" alt="">
                    <div class="col-md-12">
                      <p><strong>@{{follower.username}}</strong></p>
                    </div>
                    <div class="col-md-12">
                      <p>{{follower.username}}</p>
                    </div>
                    <div class="col-md-12" <div class="col-md-4">
                      <p class="text-primary">Tweets <br/>{{follower.tweetCount}}</p>
                    </div>
                    <div class="col-md-4">
                      <p class="text-primary">Followers <br/>{{follower.followerCount}}</p>
                    </div>
                    <div class="col-md-4">
                      <p class="text-primary">Following <br/>{{follower.followingCount}}</p>
                    </div>
                  </div>
                  <hr/>
                </router-link>
              </div>
            </div>
          </tab>
        </tabs>
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
        newTweet: '',
        myTweets: '',
        userId: '',
        user: '',
        Followers: '',
        Following: '',
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
      getUser() {
        this.$http.get('api/user/' + this.userId, null, localStorage.getItem('__token')).then((response) => {
          this.user = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
      getAllTweets() {
        this.$http.get('api/user/' + this.userId + '/tweets/', null, localStorage.getItem('__token')).then((response) => {
          this.myTweets = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
      getFollowing() {
        this.$http.get('api/user/' + this.userId + '/following/', null, localStorage.getItem('__token')).then((response) => {
          this.Following = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
      getFollowers() {
        this.$http.get('api/user/' + this.userId + '/followers/', null, localStorage.getItem('__token')).then((response) => {
          this.Followers = response.data;
        }).catch((error) => {
          self.$Message.error(error, 30);
        });
      },
    },
    mounted() {
      this.userId = this.$route.params.userId;
      this.getUser();
      this.getAllTweets();
      this.getFollowing();
      this.getFollowers();
    },
  };

</script>
