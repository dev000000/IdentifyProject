import { useField } from "formik";
import TextField from '@mui/material/TextField';
import React from "react";

const ReusableTextField = ({
  name,
  size,
  variant,
  value,
  onChange,
  ...otherProps
}) => {
  const [field, meta] = useField(name);
  const configTextField = {
    ...field,
    ...otherProps,
    fullWidth: true,
    InputLabelProps: { shrink: true },
    variant: variant ? variant : "outlined",
    size: size ? size : "medium",
  };
  if (value !== undefined) {
    configTextField.value = value;
    configTextField.onChange = onChange || (() => {});
  }
  if (meta && meta.touched && meta.error) {
    configTextField.error = true;
    configTextField.helperText = meta.error;
  }
  return <TextField {...configTextField} />;
};

export default ReusableTextField;
