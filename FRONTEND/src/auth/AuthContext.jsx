import { getAuthenticated, removeAccessToken, removeRefreshToken, setAccessToken, setAuthenticated, setRefreshToken } from "../services/localStorageService";
import { createContext, useContext, useState } from "react";

// create new context
const AuthContext = createContext();

// export function useAuth , this return AuContext
export const useAuth = () => useContext(AuthContext);
export const AuthProvider = ({ children }) => {
  // throw new Error("An error has occurred in AuthProvider component");
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    return getAuthenticated() === "true" ;
  });
  const login = (accessToken, refreshToken) => {
    setIsAuthenticated(true);
    setAuthenticated(true);
    setAccessToken(accessToken);
    setRefreshToken(refreshToken);
  }
  const logout = () => {
    removeAccessToken();
    removeRefreshToken();
    setAuthenticated(false);
    setIsAuthenticated(false);
    
  }
  return (
    <AuthContext.Provider value={{isAuthenticated, login, logout}}>
      {children}
    </AuthContext.Provider>
  )
};
