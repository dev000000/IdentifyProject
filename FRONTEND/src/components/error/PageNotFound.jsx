import { Box, Button, Typography } from "@mui/material";
import React from "react";
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
function PageNotFound() {
  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        alignItems: "center",
        background: "radial-gradient(circle, #404040 0%, #141414 100%)",
        flexDirection: "column",
      }}
    >
      <Box
        component="img"
        src="/images/page_not_found.png"
        alt="Logo"
        sx={{ width: "200px", height: "458px", mt: 10 }}
      />
      <Typography
        variant="h4"
        sx={{ color: "white", mt: 6, fontWeight: 400, fontSize: "22px" }}
      >
        Oops it seems you follow backlink
      </Typography>
      <Button
        variant="contained"
        sx={{
          mt: 3,
          fontWeight: 500,
          fontSize: "18px",
          background: "transparent",
          border: "1px solid white",
          borderRadius: "23px",
          width: "219px",
          ":hover": { background: "white", color: "black" },
        }}
        startIcon={<KeyboardBackspaceIcon />}
        onClick={() => (window.location.href = "/")}
      >
        Back To Home
      </Button>
    </Box>
  );
}

export default PageNotFound;
