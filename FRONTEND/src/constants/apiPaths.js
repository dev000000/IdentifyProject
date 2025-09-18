import ConstaintList from "../configurations/appConfig";
const API_AUTH_PATH = ConstaintList.API_ENDPOINT + "/api/v1/auth";

const PUBLIC_API_PATH = [`${API_AUTH_PATH}/login`, `${API_AUTH_PATH}/introspect`];

export default PUBLIC_API_PATH;
