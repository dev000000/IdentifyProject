import axios from "axios";
import { toast } from "react-toastify";
import {
  getAccessToken,
  getRefreshToken,
  setAccessToken,
} from "../services/localStorageService";
import PUBLIC_API_PATH from "../constants/apiPaths";
import { refreshToken, refreshTokenCookie } from "../components/Login/LoginService";
import { callLogout } from "../auth/authBridge";

// initialize axios instance to customize and define configurations fot the project
let authorizedAxiosInstance = axios.create();

// set timeout for each request : thoi gian cho moi request
authorizedAxiosInstance.defaults.timeout = 1000 * 60 * 10; // 10 minutes

// allow to send cookies with request to backend ( case save JWT tokens (refresh, access) in https-only cookies )
authorizedAxiosInstance.defaults.withCredentials = true;

// 
// authorizedAxiosInstance.defaults.xsrfCookieName = "XSRF-TOKEN",
// authorizedAxiosInstance.defaults.xsrfHeaderName = "X-XSRF-TOKEN",

// configure interceptors for request and response

// Add a request interceptor
authorizedAxiosInstance.interceptors.request.use(
  (config) => {
    // Do something before request is sent

    // THIS SECTION FOR LOCAL STORAGE

    // do not add token to request if the request is public api
    // if (PUBLIC_API_PATH.includes(config.url)) {
    //   return config;
    // }
    // // add token to header of request if exist
    // const accessToken = getAccessToken();
    // if (accessToken) {
    //   // need add Bearer , because according to OAuth2 standard , bearer is needed for define type of token
    //   config.headers.Authorization = `Bearer ${accessToken}`;
    // }
    return config;
  },
  (error) => {
    // Do something with request error
    return Promise.reject(error);
  }
);
// Create a promise for calling the refresh_token API
// The purpose of this Promise is that when the first refreshToken request comes in,
// it will hold back calling the refresh_token API until the first one finishes successfully.
// Only when will it retry the previous failed APIs, Instead of calling refresh repeatedly for each failed request.
let refreshTokenPromise = null;

// Add a response interceptor
authorizedAxiosInstance.interceptors.response.use(
  function onFulfilled(response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  async function onRejected(error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    // hanle error globally here : show toast error message for every Api call
    // do not show toast if status is 410 (GONE) , 410 serve automatically refresh token when access token expired

    const originalRequest = error.config;
    if (error.response?.status === 401) {
      toast.error(error.response?.data?.message || error.message);
      callLogout();
      return Promise.reject(error);
    }

    // if status = 410 GONE ( need refresh token )
    if (error.response?.status === 410 && originalRequest) {
      // Access Token was expired
      if (!refreshTokenPromise) {
        refreshTokenPromise = refreshTokenCookie()
          .then((res) => {
            // This is for local storage
            // const accessToken = res.data?.result?.accessToken;
            // setAccessToken(accessToken);
            // authorizedAxiosInstance.defaults.headers.Authorization = `Bearer ${accessToken}`;
          })
          .catch((_error) => {
            // If error in process refresh token => LOGOUT
            toast.error(_error.response?.data?.message || _error.message);
            callLogout();
            return Promise.reject(_error);
          })
          .finally(() => {
            refreshTokenPromise = null;
          });
      }
      return refreshTokenPromise.then(() => {
        return authorizedAxiosInstance(originalRequest);
      });
    }
    if (error.response?.status === 403 && error.response.data) {
      toast.error(error.response?.data?.message || error.message);
      return Promise.reject(error);
    }
    return Promise.reject(error);
  }
);
export default authorizedAxiosInstance;
