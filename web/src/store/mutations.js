/* eslint no-param-reassign: ["error", { "props": false }] */
export const UPDATE_AUTH = (state, auth) => {
  state.auth = auth;
};

export const UPDATE_USER = (state, user) => {
  state.user = user;
};

export const UPDATE_MYTWEETS = (state, mytweets) => {
  state.myTweets = mytweets;
};

export const SEARCH = (state, searchData) => {
  state.search = searchData;
};

export const UPDATE_MESSAGES = (state, message) => {
  if (state.messages.length === 0) {
    state.messages = [];
  }
  state.messages.push(message);
};

/**
 * Clear each property, one by one, so reactivity still works.
 *
 * (ie. clear out state.auth.isLoggedIn so Navbar component
 *  automatically reacts to logged out state,
 * and the Navbar menu adjusts accordingly)
 *
 */
export const CLEAR_ALL_DATA = (state) => {
  // Auth
  state.auth.isLoggedIn = false;
  state.auth.accessToken = null;
  state.auth.refreshToken = null;

  // User
  state.user = '';

  // Tweets
  state.myTweets = '';

  state.messages = '';
};
