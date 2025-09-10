import { getAuthenticated, removeToken, setAuthenticated, setToken } from "../services/localStorageService";
import { createContext, useContext, useState } from "react";

// create new context
const AuthContext = createContext();

// export function useAuth , this return AuContext
export const useAuth = () => useContext(AuthContext);
export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    return getAuthenticated() === "true" ;
  });
  const login = (token) => {
    setIsAuthenticated(true);
    setAuthenticated(true);
    setToken(token);
    
  }
  const logout = () => {
    setIsAuthenticated(false);
    setAuthenticated(false);
    removeToken();
  }
  return (
    <AuthContext.Provider value={{isAuthenticated, login, logout}}>
      {children}
    </AuthContext.Provider>
  )
};
