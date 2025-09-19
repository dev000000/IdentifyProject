import { Navigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext"

const ProtectedRoute = ({children}) => {
  const {isAuthenticated} = useAuth();
  if(!isAuthenticated){
    return <Navigate to="/login" />;
  }else {
    return children;
  }

}
export default ProtectedRoute;