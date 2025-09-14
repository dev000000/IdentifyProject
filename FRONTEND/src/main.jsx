import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { AuthProvider } from "./auth/AuthContext.jsx";
import { ErrorBoundary } from "react-error-boundary";
import ErrorPageNew from "./components/error/ErrorPageNew.jsx";
// import ErrorBoundary from "./errorHandling/ErrorBoundary.jsx";
const theme = createTheme({
  palette: {
    primary: {
      main: "#013e87",
    },
    secondary: {
      main: "#013481",
    },
    background: {
      default: "#E5E5E5",
      paper: "#FFFFFF",
      notFound: "radial-gradient(circle, #404040 0%, #141414 100%)",
    },
    text: {
      one: "rgba(0, 0, 0, 1)",
      two: "rgba(26, 41, 61, 0.83)",
      three: "rgba(22, 118, 243, 1)",
      four: "rgba(27, 43, 65, 0.72)",
      five: "rgba(255, 255, 255, 1)",
      six: "rgba(28, 52, 84, 0.26)",
      seven: "rgba(28, 55, 90, 0.16)",
      eight: "rgba(24, 39, 58, 0.94)",
      nine: "rgba(25, 59, 103, 0.05)",
      ten: "rgba(26, 41, 61, 0.83)",
      eleven: "rgba(28, 46, 69, 0.6)",
      twelve: "rgba(28, 52, 84, 0.37)",
      thirteen: "rgba(51, 51, 51, 1)",
    },
  },
  typography: {
    fontFamily: "Plus Jakarta Sans, sans-serif",
    h1: {
      fontSize: "22px",
      fontWeight: 600,
    },
    h2: {
      fontSize: "1.75rem",
      fontWeight: 600,
    },
    h3: {
      fontSize: "22px",
      fontWeight: 600, // semibold
    },
    body1: {
      fontSize: "14px",
      fontWeight: 400, // regular
    },
  },
});
createRoot(document.getElementById("root")).render(
  // <StrictMode>
  <ErrorBoundary FallbackComponent={ErrorPageNew}>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AuthProvider>
        <App />
      </AuthProvider>
    </ThemeProvider>
  </ErrorBoundary>
  // </StrictMode>
);
