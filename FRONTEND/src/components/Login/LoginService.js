import axios from "axios";
import ConstaintList from "../../configurations/appConfig";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/auth";

export const loginWithUserAndPass = (userObject) => {
  var url = API_PATH + "/token";
  return axios.post(url,userObject);
}
export const checkToken = (tokenObject) => {
  var url = API_PATH + "/introspect";
  return axios.post(url,tokenObject);
}
