import { useRouteError } from "react-router-dom";
import { Box, Typography, Paper, Button } from "@mui/material";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

export default function ErrorPageNew(props) {
  let { error, resetErrorBoundary } = props;

  if (!error) {
    resetErrorBoundary = null;
  }
  // If error is passed as prop, use it; otherwise, use useRouteError
  if (!error) {
    error = useRouteError();
  }
  console.error(error);
  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        bgcolor: "background.default",
        p: 2,
      }}
    >
      <Paper
        elevation={3}
        sx={{
          p: 4,
          maxWidth: 480,
          textAlign: "center",
          borderRadius: 3,
        }}
      >
        <ErrorOutlineIcon color="error" sx={{ fontSize: 60, mb: 2 }} />
        <Typography variant="h4" component="h1" gutterBottom>
          Oops!
        </Typography>
        <Typography variant="body1" sx={{ mb: 2 }}>
          Sorry, an unexpected error has occurred.
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
          {error?.statusText || error?.message}
        </Typography>
        <Button
          variant="contained"
          color="primary"
          onClick={() => (window.location.href = "/")}
        >
          Go Home
        </Button>
        {resetErrorBoundary && (
          <Button
            variant="contained"
            color="primary"
            onClick={resetErrorBoundary}
          >
            Reload Page
          </Button>
        )}
      </Paper>
    </Box>
  );
}
