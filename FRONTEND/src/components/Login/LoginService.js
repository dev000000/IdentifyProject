import authorizedAxiosInstance from "../../utils/authorizedAxiosInstance";
import ConstaintList from "../../configurations/appConfig";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/auth";
const API_PATH_2 = ConstaintList.API_ENDPOINT + "/api/v1/users";

export const loginWithUserAndPass = (userObject) => {
  var url = API_PATH + "/login";
  return authorizedAxiosInstance.post(url,userObject);
}
export const checkToken = (tokenObject) => {
  var url = API_PATH + "/introspect";
  return authorizedAxiosInstance.post(url,tokenObject);
}
export const refreshToken = (refreshToken) => {
  var url = API_PATH + "/refresh-token";
  return authorizedAxiosInstance.post(url, { refreshToken });
}

export const signUp = (userObject) => {
  var url = API_PATH + "/register" ;
  return authorizedAxiosInstance.post(url,userObject);
}
export const getMyProfile = ({config = {}}) => {
  var url = API_PATH_2 + "/my-profile";
  return authorizedAxiosInstance.get(url, config);
};
export const logOut = () => {
  var url = API_PATH + "/logout";
  return authorizedAxiosInstance.post(url);
}
// HTTPS ONLY COOKIES
export const refreshTokenCookie = () => {
  var url = API_PATH + "/refresh-token";
  return authorizedAxiosInstance.post(url);
}