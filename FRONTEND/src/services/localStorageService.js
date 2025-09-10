const KEY_TOKEN = "accessToken";
const KEY_AUTH = "isAuthenticated";
export const setToken = (token) => {
  localStorage.setItem(KEY_TOKEN,token);
}
export const getToken = () => {
  return localStorage.getItem(KEY_TOKEN);
}
export const removeToken = () => {
  localStorage.removeItem(KEY_TOKEN);
}
export const setAuthenticated = (boolean) => {
  localStorage.setItem(KEY_AUTH,boolean);
}
export const getAuthenticated = () => {
  return localStorage.getItem(KEY_AUTH);
}
