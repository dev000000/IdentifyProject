import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../components/Login/LoginIndex";
import Home from "../components/Home/HomeIndex";
import SignUp from "../components/SignUp/SignUpIndex";
import ProtectedRoute from "./ProtectedRoute";

const router = createBrowserRouter([
  {
    path: "/",
    element: <ProtectedRoute> <Home /> </ProtectedRoute>,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/sign-up",
    element: <SignUp />,
  },
]);

function AppRoutes() {
  return <RouterProvider router={router} />;
}

export default AppRoutes;
