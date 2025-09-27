import {
  getAuthenticated,
  removeAccessToken,
  removeRefreshToken,
  setAccessToken,
  setAuthenticated,
  setRefreshToken,
} from "../services/localStorageService";
import { createContext, useContext, useEffect, useState } from "react";
import { registerLogout } from "./authBridge";
import { logOut } from "../components/Login/LoginService";

// create new context
const AuthContext = createContext();

// export function useAuth , this return AuContext
export const useAuth = () => useContext(AuthContext);
export const AuthProvider = ({ children }) => {
  // throw new Error("An error has occurred in AuthProvider component");
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    return getAuthenticated() === "true";
  });
  const login = (accessToken, refreshToken) => {
    setIsAuthenticated(true);
    setAuthenticated(true);
    setAccessToken(accessToken);
    setRefreshToken(refreshToken);
  };
  const logout = async () => {
    // remove token in localstorage
    try {
      // call api logout backend
      await logOut();
    } finally {
      removeAccessToken();
      removeRefreshToken();
      // remove isAuthentication in localstorage
      setAuthenticated(false);
      // set state
      setIsAuthenticated(false);
    }
  };
  useEffect(() => {
    registerLogout(logout);
  }, [logout]);
  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
