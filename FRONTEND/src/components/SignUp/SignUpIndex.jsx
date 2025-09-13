import React, { useEffect, useState } from "react";
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
import { Form, Formik } from "formik";
import ReusableTextField from "../common/ReusableTextField";
import ReusableDateTimePicker from "../common/ReusableDateTimePicker";
import { signUp } from "./SignUpService";
import { DATA_CODE } from "../../dataCode";
import * as Yup from "yup";
import { isBefore, startOfDay, subYears } from "date-fns";

// validation schema
const validationSchema = Yup.object({
  firstName: Yup.string().required("Không được để trống"),
  lastName: Yup.string().required("Không được để trống"),
  userName: Yup.string()
    .required("Không được để trống")
    .min(10, "Tên đăng nhập nằm trong khoảng 10-20 ký tự")
    .max(20, "Tên đăng nhập nằm trong khoảng 10-20 ký tự"),
  dob: Yup.date().required("Không được để trống").test(
    "dob",
    "Bạn phải từ 10 tuổi để đăng ký",
    (value) => {
      if(!value) return false;
      const minDate = startOfDay(subYears(new Date(), 10));
      const dob = startOfDay(value);
      return isBefore(dob, minDate) || dob.getTime() === minDate.getTime();
    }
  ),
  passWord: Yup.string()
    .required("Không được để trống")
    .min(10, "Mật khẩu nằm trong khoảng 10-20 ký tự")
    .max(20, "Mật khẩu nằm trong khoảng 10-20 ký tự"),
  confirmPassword: Yup.string()
    .required("Không được để trống")
    .oneOf([Yup.ref("passWord"), null], "Mật khẩu không khớp"),
});

function SignUpIndex() {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {})();
    return () => {
      // cleanup
    };
  }, []);

  // handle sign up
  const handleSignUp = async (values) => {
    const newValues = {
      firstName: values.firstName,
      lastName: values.lastName,
      userName: values.userName,
      dob: values.dob,
      passWord: values.passWord,
    };
    try {
      const response = await signUp(newValues);
      if (response.data.code === DATA_CODE.OK) {
        alert("Sign Up Successfully");
        navigate("/login");
      }
    } catch (error) {
      console.log(error);
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
          overflowY: "auto",
        }}
      >
        {/* Section Logo */}
        <Box
          component="img"
          src="/images/Logo.png"
          alt="Logo"
          sx={{ width: "168px", height: "92px", mt: "31px" }}
        />
        {/* Section Text Under Logo */}
        <Typography variant="h3">Create an Account</Typography>
        <Typography sx={{ color: "text.ten" }}>
          Sign up now to get started with an account.
        </Typography>
        <Box sx={{ width: "100%" }}>
          <Formik
            initialValues={{
              firstName: "",
              lastName: "",
              userName: "",
              dob: "",
              passWord: "",
              confirmPassword: "",
            }}
            validationSchema={validationSchema}
            onSubmit={(values) => {
              handleSignUp(values);
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
                      Sign up with Google
                    </Typography>
                  </Box>
                </Button>
                <Divider
                  sx={{ fontSize: "14px", fontWeight: 400, color: "text.six" }}
                >
                  OR
                </Divider>
                <ReusableTextField label="First Name" name="firstName" />
                <ReusableTextField label="Last Name" name="lastName" />
                <ReusableTextField label="Username" name="userName" />
                <ReusableDateTimePicker label="Date of Birth" name="dob" />

                <ReusableTextField
                  label="Password"
                  name="passWord"
                  type={showPassword ? "text" : "password"}
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
                <ReusableTextField
                  label="Confirm Password"
                  name="confirmPassword"
                  type={showConfirmPassword ? "text" : "password"}
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() => setShowConfirmPassword((s) => !s)}
                          edge="end"
                        >
                          {showConfirmPassword ? (
                            <VisibilityOff />
                          ) : (
                            <Visibility />
                          )}
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />

                <Box sx={{ display: "flex", alignItems: "center" }}>
                  <CustomizedCheckbox
                    defaultChecked
                    sx={{ "&.MuiCheckbox-root": { p: 0 }, mr: 1 }}
                  />
                  <Box
                    sx={{
                      display: "flex",
                      justifyContent: "center",
                      gap: "2px",
                    }}
                  >
                    <Typography sx={{ color: "", fontSize: "13px" }}>
                      I have read and agree to
                    </Typography>
                    <Typography
                      sx={{
                        color: "text.three",
                        fontSize: "13px",
                        fontWeight: 600,
                        "&:hover": { textDecoration: "underline" },
                      }}
                      component={Link}
                      to={"/term-of-service"}
                    >
                      Terms of Service
                    </Typography>
                  </Box>
                </Box>
                <Button
                  variant="contained"
                  sx={{
                    p: 2,
                    textTransform: "none",
                    color: "text.five",
                    bgcolor: "text.three",
                    fontSize: "16px",
                    fontWeight: 600,
                  }}
                  type="submit"
                >
                  Get Started
                </Button>
                <Box
                  sx={{
                    display: "flex",
                    justifyContent: "center",
                    gap: "2px",
                    pb: 2,
                  }}
                >
                  <Typography sx={{ color: "", fontSize: "13px" }}>
                    Already have an account?
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
                    to={"/login"}
                  >
                    Login
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

export default SignUpIndex;
