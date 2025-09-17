import axios from "axios"
import { toast } from "react-toastify";

// initialize axios instance to customize and define configurations fot the project
let authorizedAxiosInstance = axios.create();
// set timeout for each request : thoi gian cho moi request
authorizedAxiosInstance.defaults.timeout = 1000 * 60 * 10; // 10 minutes

// allow to send cookies with request to backend ( case save JWT tokens (refresh, access) in https-only cookies )
// authorizedAxiosInstance.defaults.withCredentials = true; 


// configure interceptors for request and response

// Add a request interceptor
authorizedAxiosInstance.interceptors.request.use((config) =>{
    // Do something before request is sent
    return config;
  }, (error) => {
    // Do something with request error
    return Promise.reject(error);
  },
);

// Add a response interceptor
authorizedAxiosInstance.interceptors.response.use(function onFulfilled(response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  }, function onRejected(error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    // hanle error globally here : show toast error message for every Api call
    // do not show toast if status is 410 (GONE) , 410 serve automatically refresh token when access token expired

    if(error.response?.status !== 410) {
      toast.error(error.response?.data?.message || error.message);
    }
    return Promise.reject(error);
  });
export default authorizedAxiosInstance;