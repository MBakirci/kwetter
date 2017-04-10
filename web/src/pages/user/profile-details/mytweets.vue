<template>
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
</template>
