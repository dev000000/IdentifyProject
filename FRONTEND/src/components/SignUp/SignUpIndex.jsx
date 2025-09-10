import React from "react";
import Box from "@mui/material/Box";
import {
  Button,
  Divider,
  IconButton,
  InputAdornment,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import CustomizedCheckbox from "../common/CustomizedCheckBox";
import { Link } from "react-router-dom";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import dayjs from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

function SignUpIndex() {
  const [showPassword, setShowPassword] = React.useState(false);
  const [value, setValue] = React.useState(dayjs("2022-04-07"));
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
        <Box
          component="img"
          src="/images/Logo.png"
          alt="Logo"
          sx={{ width: "168px", height: "92px", mt: "31px" }}
        />
        <Typography variant="h3">Create an Account</Typography>
        <Typography sx={{ color: "text.ten" }}>
          Sign up now to get started with an account.
        </Typography>
        <Box
          sx={{
            mx: 3,
            height: "50vh",
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
                sx={{ fontSize: "16px", fontWeight: 600, color: "text.four" }}
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
          <TextField
            label="First Name"
            name="firstName"
            // value={values.email}
            // onChange={handleChange}
            // error={Boolean(errors.email)}
            // helperText={errors.email}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Last Name"
            name="lastName"
            // value={values.email}
            // onChange={handleChange}
            // error={Boolean(errors.email)}
            // helperText={errors.email}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Username"
            name="userName"
            // value={values.email}
            // onChange={handleChange}
            // error={Boolean(errors.email)}
            // helperText={errors.email}
            fullWidth
            margin="normal"
          />
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              disableFuture
              label="Date of Birth"
              name="dob"
              openTo="year"
              views={["year", "month", "day"]}
              value={value}
              onChange={(newValue) => {
                setValue(newValue);
              }}
              renderInput={(params) => <TextField {...params} />}
            />
          </LocalizationProvider>

          <TextField
            label="Password"
            name="password"
            // type={showPassword ? "text" : "password"}
            // value={values.password}
            // onChange={handleChange}
            // error={Boolean(errors.password)}
            // helperText={errors.password}
            fullWidth
            margin="normal"
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
          <TextField
            label="Confirm Password"
            name="confirmPassword"
            // type={showPassword ? "text" : "password"}
            // value={values.password}
            // onChange={handleChange}
            // error={Boolean(errors.password)}
            // helperText={errors.password}
            fullWidth
            margin="normal"
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

          <Box sx={{ display: "flex", alignItems: "center" }}>
            <CustomizedCheckbox
              defaultChecked
              sx={{ "&.MuiCheckbox-root": { p: 0 }, mr: 1 }}
            />
            <Box sx={{ display: "flex", justifyContent: "center", gap: "2px" }}>
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
          >
            Get Started
          </Button>
          <Box sx={{ display: "flex", justifyContent: "center", gap: "2px", pb: 2 }}>
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
      </Paper>
    </Box>
  );
}

export default SignUpIndex;
