export const STORAGE_KEY = 'kwetter';

let syncedData = {
  auth: {
    isLoggedIn: false,
    accessToken: null,
    refreshToken: null,
  },
  user: '',
  myTweets: '',
};

const notSyncedData = {
  messages: [],
  search: {
    searchText: '',
  },
};

// Sync with local storage.
if (localStorage.getItem(STORAGE_KEY)) {
  syncedData = JSON.parse(localStorage.getItem(STORAGE_KEY));
}

// Merge data and export it.
export const state = Object.assign(syncedData, notSyncedData);
