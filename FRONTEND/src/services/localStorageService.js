const KEY_ACCESS_TOKEN = "accessToken";
const KEY_REFRESH_TOKEN = "refreshToken";
const KEY_AUTH = "isAuthenticated";
export const setAccessToken = (token) => {
  localStorage.setItem(KEY_ACCESS_TOKEN,token);
}
export const setRefreshToken = (token) => {
  localStorage.setItem(KEY_REFRESH_TOKEN,token);
}
export const getAccessToken = () => {
  return localStorage.getItem(KEY_ACCESS_TOKEN);
}
export const getRefreshToken = () => {
  return localStorage.getItem(KEY_REFRESH_TOKEN);
}
export const removeAccessToken = () => {
  localStorage.removeItem(KEY_ACCESS_TOKEN);
}
export const removeRefreshToken = () => {
  localStorage.removeItem(KEY_REFRESH_TOKEN);
}
export const setAuthenticated = (boolean) => {
  localStorage.setItem(KEY_AUTH,boolean);
}
export const getAuthenticated = () => {
  return localStorage.getItem(KEY_AUTH);
}
