import { useEffect, useState } from "react";

import HeaderIndex from "../Header/HeaderIndex";
import Box from "@mui/material/Box";
import {
  Divider,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { getMyProfile } from "./HomeService";

function HomeIndex() {
  const [userDetail, setUserDetails] = useState({});
  useEffect(() => {
    const getUserDetails = async () => {
      try {
        const response = await getMyProfile();
        setUserDetails(response.data.result);
        console.log(response.data.result);
      } catch (error) {
        console.error("Error fetching user details:", error);
      }
    };
    getUserDetails();
  }, []);
  return (
    <>
      <HeaderIndex />
      {userDetail && (
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
            <Box
              component="img"
              src="/images/Logo.png"
              alt="Logo"
              sx={{ width: "168px", height: "92px", mt: 8 }}
            />
            <Typography variant="h3">You have login</Typography>
            <Typography sx={{ color: "text.ten" }}>
              Welcome back, this is your infor
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
              <Divider
                sx={{ fontSize: "14px", fontWeight: 400, color: "text.six" }}
              ></Divider>
              <TextField
                label="Username"
                name="userName"
                value={userDetail.userName}
                fullWidth
                margin="normal"
              />
              <Box
                sx={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "space-between",
                }}
              ></Box>
            </Box>
          </Paper>
        </Box>
      )}
    </>
  );
}

export default HomeIndex;
