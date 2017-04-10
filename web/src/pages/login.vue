<template>
  <div>
    <h1>KWETTER</h1>
    <div class="panel panel-primary col-md-6 col-md-offset-3">
      <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
      </div>
      <div class="panel-body">
        <form class="form-horizontal">
          <fieldset>
            <div class="form-group">
              <label for="usernameInput" class="col-lg-2 control-label">Username</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="usernameInput" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-lg-2 control-label">Password</label>
              <div class="col-lg-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                <div class="checkbox">
                </div>
              </div>
            </div>
            <div class="form-group">
            <router-link to="/register"><p>Not an account? Register here.</p></router-link>
              <button type="submit" class="btn btn-primary">Login</button>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</template>
