import axios from 'axios';
import config from '../../config';

const baseURL = (process.env.NODE_ENV !== 'production' ? config.dev.env.API_URL : config.prod.env.API_URL).replace(/['"]+/g, '');

export default {
  get(url, params) {
    return axios({
      method: 'get',
      url: baseURL + url,
      params,
      timeout: 30000,
    });
  },
  post(url, data) {
    return axios({
      method: 'post',
      url: baseURL + url,
      data,
      timeout: 30000,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      },
    });
  },
  form(url, formdata) {
    return axios({
      method: 'post',
      url: baseURL + url,
      data: formdata,
      timeout: 30000,
    });
  },
};
