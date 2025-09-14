
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../components/Login/LoginIndex";
import Home from "../components/Home/HomeIndex";
import SignUp from "../components/SignUp/SignUpIndex";
import ProtectedRoute from "./ProtectedRoute";
import ErrorPage from "../components/error/ErrorPage";
import PageNotFound from "../components/error/PageNotFound";
import HomeInside from "../components/HomeInside/HomeInsideIndex";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/sign-up",
    element: <SignUp />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/home-inside",
    element: (
      <ProtectedRoute>
        <HomeInside/>
      </ProtectedRoute>
    ),
    errorElement: <ErrorPage />,
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
