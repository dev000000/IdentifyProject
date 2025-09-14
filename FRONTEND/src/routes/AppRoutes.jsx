
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../components/Login/LoginIndex";
import Home from "../components/Home/HomeIndex";
import SignUp from "../components/SignUp/SignUpIndex";
import ProtectedRoute from "./ProtectedRoute";
import ErrorPage from "../components/error/ErrorPage";
import PageNotFound from "../components/error/PageNotFound";
import HomeInside from "../components/HomeInside/HomeInsideIndex";
import ErrorPageNew from "../components/error/ErrorPageNew";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/sign-up",
    element: <SignUp />,
    errorElement: <ErrorPageNew />,
  },
  {
    path: "/home-inside",
    element: (
      <ProtectedRoute>
        <HomeInside/>
      </ProtectedRoute>
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
