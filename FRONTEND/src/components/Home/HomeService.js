import axios from "axios";
import ConstaintList from "../../configurations/appConfig";
import { getToken } from "../../services/localStorageService";
const API_PATH = ConstaintList.API_ENDPOINT + "/api/v1/users";

export const getMyProfile = () => {
  const token = getToken();
  var url = API_PATH + "/my-profile";
  return axios.get(url, {
    headers: {
      Authorization: `Bearer ${token}`,
    }
  }
  );
}
