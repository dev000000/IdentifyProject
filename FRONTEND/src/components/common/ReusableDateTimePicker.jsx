import React from "react";
import TextField from "@mui/material/TextField";
import { useField } from "formik";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

const ReusableDateTimePicker = ({
  name,
  size,
  variant,
  value,
  type,
  onChange,
  format,
  ...otherProps
}) => {
  const [field, meta] = useField(name);
  const configDateTimePicker = {
    ...field,
    ...otherProps,
    fullWidth: true,
    type: type ? type : "date",
    InputLabelProps: { shrink: true },
    variant: variant ? variant : "outlined",
    format: format ? format : "MM/dd/yyyy",
    size: size ? size : "medium",
  };
  if (value !== undefined) {
    configDateTimePicker.value = value;
    configDateTimePicker.onChange = onChange || (() => {});
  }
  if (meta && meta.touched && meta.error) {
    configDateTimePicker.error = true;
    configDateTimePicker.helperText = meta.error;
  }
  return <TextField {...configDateTimePicker} />;
};

export default ReusableDateTimePicker;
