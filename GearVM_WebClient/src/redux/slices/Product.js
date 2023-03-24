import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { toast } from "react-toastify";
import { url } from "../../API/api";

const initialState = {
  item: [],
  TotalPage: 0,
  productStatus: "",
  productError: "",
};
// export const registerUser = createAsyncThunk(
//   "auth/registerUser",
//   async (values, { rejectWithValue }) => {
//     try {
//       await axios.post(`${url}/customers/register`, {
//         name: values.name,
//         dateOfBirth: values.dateOfBirth,
//         gender: values.gender,
//         email: values.email,
//         phoneNumber: values.phoneNumber,
//         password: values.password,
//       });
//     } catch (error) {
//       console.log(error.reponse.data);
//       return rejectWithValue(error.reponse.data);
//     }
//   }
// );

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {},
  //   extraReducers: (builder) => {
  //     builder.addCase(registerUser.pending, (state, action) => {
  //       return { ...state, registerStatus: "pending" };
  //     });
  //     builder.addCase(registerUser.fulfilled, (state, action) => {
  //       return {
  //         ...state,
  //         registerStatus: "succes",
  //       };
  //       // return { ...state, userLoaded: true };
  //     });
  //     builder.addCase(registerUser.rejected, (state, action) => {
  //       return {
  //         ...state,
  //         registerError: action.payload,
  //         registerStatus: "rejected",
  //       };
  //     });
  //   },
});
