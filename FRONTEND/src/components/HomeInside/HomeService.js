import authorizedAxiosInstance from "../../utils/authorizedAxiosInstance";
import ConstaintList from "../../configurations/appConfig";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/users";

export const getMyProfile = () => {
  var url = API_PATH + "/my-profile";
  return authorizedAxiosInstance.get(url);
};
