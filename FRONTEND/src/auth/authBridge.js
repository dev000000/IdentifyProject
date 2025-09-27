let logoutRef = null;

export const registerLogout = (fn) => {
  logoutRef = fn;
};

export const callLogout = () => {
  if (logoutRef) {
    logoutRef();
  }
};