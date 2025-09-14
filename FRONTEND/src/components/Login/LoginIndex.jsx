import { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import {
  Button,
  Divider,
  IconButton,
  InputAdornment,
  Paper,
  Typography,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import CustomizedCheckbox from "../common/CustomizedCheckBox";
import { Link, useNavigate } from "react-router-dom";
import ReusableTextField from "../common/ReusableTextField";
import { Form, Formik } from "formik";
import { checkToken, loginWithUserAndPass } from "./LoginService";
import { useAuth } from "../../auth/AuthContext";
import { getToken } from "../../services/localStorageService";

function LoginIndex() {
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();
  const { isAuthenticated, login, logout } = useAuth();
  // throw Error here and ErrorBoundary of react-router-dom will catch it
  // throw new Error("An error has occurred in LoginIndex component");
  useEffect(() => {
    // anonymous function : ham an danh 
    // async () => { .... }
    // ( async () => {.....} ) declaration => expression
    // ( async () => { ..... })() goi
    // IIFE (Immediately Invoked Function Expression) : Ham tu goi ngay lap tuc
    (async () => {
      if (!isAuthenticated) return null;
      const token = getToken();
      if (token) {
        try {
          const response = await checkToken({ token });
          console.log(response);
          navigate("/home-inside");
        } catch (error) {
          console.log(error);
          logout();
          navigate("/login");
        }
      }
    })();
  }, [isAuthenticated, navigate, logout]);

  const Login = async (userObject) => {
    // error in event handler (onClick, onSubmit, onChange,...) will not be caught by ErrorBoundary
    // throw new Error("An error has occurred in Login function");
    try {
      const response = await loginWithUserAndPass(userObject);
      console.log(response);
      console.log(response.data.code);
      console.log(response.data.result);
      login(response.data.result.token);
      navigate("/");
    } catch (error) {
      console.log(error.response.response.code);
      console.log(error.response.response.message);
    }
  };
  return (
    <Box
      sx={{
        minHeight: "100vh",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Paper
        elevation={0}
        sx={{
          width: "100%",
          maxWidth: "390px",
          height: { xs: "100vh", sm: "80vh", md: "80vh" },
          display: "flex",
          alignItems: "center",
          flexDirection: "column",
        }}
      >
        {/* Section Logo */}
        <Box
          component="img"
          src="/images/Logo.png"
          alt="Logo"
          sx={{ width: "168px", height: "92px", mt: 8 }}
        />
        {/* Section Text Under Logo */}
        <Typography variant="h3">Log in to your Account </Typography>
        <Typography sx={{ color: "text.ten" }}>
          Welcome back, please enter your details.
        </Typography>

        <Box sx={{ width: "100%" }}>
          <Formik
            initialValues={{ userName: "", passWord: "" }}
            onSubmit={(values) => {
              Login(values);
            }}
          >
            <Form autoComplete="off">
              <Box
                sx={{
                  mx: 3,
                  width: "calc(100% - 56px)",
                  mt: 3,
                  gap: 2,
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                {/* Section Login google */}
                <Button
                  variant="outlined"
                  sx={{
                    width: "100%",
                    p: 2,
                    textTransform: "none",
                    border: "text.twelve",
                  }}
                >
                  <Box sx={{ display: "flex", alignItems: "center" }}>
                    <Box
                      component="img"
                      src="/images/Google__G__Logo 1.png"
                      alt="Logo"
                      sx={{ width: "19px", height: "19px", mr: "20px" }}
                    />
                    <Typography
                      sx={{
                        fontSize: "16px",
                        fontWeight: 600,
                        color: "text.four",
                      }}
                    >
                      Continue with Google{" "}
                    </Typography>
                  </Box>
                </Button>
                {/* Divider */}
                <Divider
                  sx={{ fontSize: "14px", fontWeight: 400, color: "text.six" }}
                >
                  OR
                </Divider>
                <ReusableTextField
                  label="Username"
                  name="userName"
                />
                <ReusableTextField
                  label="Password"
                  name="passWord"
                  type={showPassword ? "text" : "password"}
                  autoComplete="current-password"
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() => setShowPassword((s) => !s)}
                          edge="end"
                        >
                          {showPassword ? <VisibilityOff /> : <Visibility />}
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />
                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                  }}
                >
                  <Box sx={{ display: "flex", alignItems: "center" }}>
                    <CustomizedCheckbox
                      defaultChecked
                      sx={{ "&.MuiCheckbox-root": { p: 0 }, mr: 1 }}
                    />
                    <Typography
                      sx={{
                        fontSize: "13px",
                        color: "text.four",
                        fontWeight: 400,
                      }}
                    >
                      Remember me
                    </Typography>
                  </Box>
                  <Typography
                    sx={{
                      fontSize: "13px",
                      color: "text.thirteen",
                      fontWeight: 600,
                      textDecoration: "none",
                      "&:hover": { textDecoration: "underline" },
                    }}
                    component={Link}
                    to="/forgot-password"
                  >
                    Forgot Password?
                  </Typography>
                </Box>
                <Button
                  variant="contained"
                  type="submit"
                  sx={{
                    p: 2,
                    textTransform: "none",
                    color: "text.five",
                    bgcolor: "text.three",
                    fontSize: "16px",
                    fontWeight: 600,
                  }}
                >
                  Log in
                </Button>
                {/*  Section Don't have an account */}
                <Box
                  sx={{ display: "flex", justifyContent: "center", gap: "2px" }}
                >
                  <Typography sx={{ color: "", fontSize: "13px" }}>
                    Don’t have an account?
                  </Typography>
                  <Typography
                    sx={{
                      color: "text.three",
                      fontSize: "13px",
                      fontWeight: 600,
                      textDecoration: "none",
                      "&:hover": { textDecoration: "underline" },
                    }}
                    component={Link}
                    to={"/sign-up"}
                  >
                    Sign Up
                  </Typography>
                </Box>
              </Box>
            </Form>
          </Formik>
        </Box>
      </Paper>
    </Box>
  );
}

export default LoginIndex;
