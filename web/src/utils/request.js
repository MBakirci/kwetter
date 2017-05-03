import axios from 'axios';
import config from '../../config';


const baseURL = (process.env.NODE_ENV !== 'production' ? config.dev.env.API_URL : config.prod.env.API_URL).replace(/['"]+/g, '');

export default {
  getHeaders() {
    return {
      'Content-Type': 'application/json',
    };
  },
  get(url, params, auth) {
    return axios({
      method: 'get',
      url: baseURL + url,
      params,
      timeout: 30000,
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + auth,
      },
    });
  },
  post(url, data) {
    return axios({
      method: 'post',
      url: baseURL + url,
      data,
      headers: this.getHeaders(),
    });
  },
  form(url, formdata) {
    return axios({
      method: 'post',
      url: baseURL + url,
      data: formdata,
      timeout: 30000,
      headers: this.getHeaders(),
    });
  },
};
