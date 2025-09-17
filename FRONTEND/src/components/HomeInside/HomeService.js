import authorizedAxiosInstance from "../../utils/authorizedAxiosInstance";
import ConstaintList from "../../configurations/appConfig";
import { getAccessToken } from "../../services/localStorageService";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/users";

export const getMyProfile = () => {
  const accessTokentoken = getAccessToken();
  var url = API_PATH + "/my-profile";
  return authorizedAxiosInstance.get(url, {
    headers: {
      Authorization: `Bearer ${accessTokentoken}`,
    },
  });
};
