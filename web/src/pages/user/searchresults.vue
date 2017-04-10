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
    <div class="col-md-12" v-for="n in 10">
      <div class="user">
        <div class="row">
          <div class="col-md-1">
            <img src="http://lorempixel.com/200/200/people/" width="50px" height="50px" alt="">
          </div>
          <div class="col-md-11">
            <p>@Mehmet{{n}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
