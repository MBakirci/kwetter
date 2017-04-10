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
        <strong>Mehmet0</strong>
        <br/>
        <img src="http://lorempixel.com/200/200/people/" width="200px" height="200px" alt="">
      </div>
      <div class="col-col-md-12">
        <tabs v-model="activeTab" nav-style="tabs" justified>
          <tab header="Tweets">
            <myTweets></myTweets>
          </tab>
          <tab header="Following">
            <follow></follow>
          </tab>
          <tab header="Followers">
            <follow></follow>
          </tab>
        </tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import { tabs, tab } from 'vue-strap';
  import myTweets from './profile-details/mytweets';
  import follow from './profile-details/follow';

  export default {
    components: { tabs, tab, myTweets, follow },
    data() {
      return {
        show: false,
        newTweet: '',
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
    },
  };

</script>
