import { Navigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext"

const UnAuthorizedRoutes = ({children}) => {
  const {isAuthenticated} = useAuth();
  if(isAuthenticated){
    return <Navigate to="/home-inside" />;
  }else {
    return children;
  }

}
export default UnAuthorizedRoutes;