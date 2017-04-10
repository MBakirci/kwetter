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
        <div class="col-md-2">
          <p class="text-primary">Tweets</p>
          <p class="text-primary">0</p>
        </div>
        <div class="col-md-2">
          <p class="text-primary">Followers</p>
          <p class="text-primary">0</p>
        </div>
        <div class="col-md-2">
          <p class="text-primary">Following</p>
          <p class="text-primary">0</p>
        </div>
        <div class="col-md-2">
          <button class="btn btn-primary" @click="show = true">Add Tweet</button>
        </div>
      </div>
    </div>
    <div class="col-md-12">
      <div class="tweet" v-for="n in 10">
        <div class="col-md-12">
          <div class="col-md-9">
            <p><strong>Mehmet0</strong> @Mehmet0 - 4 weeks ago</p>
          </div>
          <div class="col-md-3">
            <i class="fa fa-heart" aria-hidden="true"></i> 0
          </div>
          <div class="col-md-12">
            <p>Hallo{{n}}</p>
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
