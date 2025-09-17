import authorizedAxiosInstance from "../../utils/authorizedAxiosInstance";
import ConstaintList from "../../configurations/appConfig";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/users";

export const signUp = (userObject) => {
  var url = API_PATH ;
  return authorizedAxiosInstance.post(url,userObject);
}

