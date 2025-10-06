import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../components/Login/LoginIndex";
import Home from "../components/Home/HomeIndex";
import SignUp from "../components/SignUp/SignUpIndex";
import ProtectedRoutes from "./ProtectedRoutes";
import PageNotFound from "../components/error/PageNotFound";
import HomeInside from "../components/HomeInside/HomeInsideIndex";
import ErrorPageNew from "../components/error/ErrorPageNew";
import UnAuthorizedRoutes from "./UnAuthorizedRoutes";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/login",
    element: (
      <UnAuthorizedRoutes>
        <Login />
      </UnAuthorizedRoutes>
    ),
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/sign-up",
    element: (
      <UnAuthorizedRoutes>
        <SignUp />
      </UnAuthorizedRoutes>
    ),
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/home-inside",
    element: (
      <ProtectedRoutes>
        <HomeInside />
      </ProtectedRoutes>
    ),
    errorElement: <ErrorPageNew />,
  },
  {
    path: "*",
    element: <PageNotFound />,
  },
]);

function AppRoutes() {
  return <RouterProvider router={router} />;
}

export default AppRoutes;
