import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { toast } from "react-toastify";
import { url } from "../../API/api";

const initialState = {
  token: localStorage.getItem("token"),
  name: "",
  email: "",
  registerStatus: "",
  registerError: "",
  loginStatus: "",
  loginError: "",
  currentStatus: "",
  currentError: "",
  emailStatus: false,
  emailError: "",
  user: [],
  userLoaded: false,
};
export const registerUser = createAsyncThunk(
  "auth/registerUser",
  async (values, { rejectWithValue }) => {
    try {
      await axios.post(`${url}/customers/register`, {
        name: values.name,
        dateOfBirth: values.dateOfBirth,
        gender: values.gender,
        email: values.email,
        phoneNumber: values.phoneNumber,
        password: values.password,
      });
    } catch (error) {
      console.log(error.reponse.data);
      return rejectWithValue(error.reponse.data);
    }
  }
);
export const loginUser = createAsyncThunk(
  "auth/login",
  async (values, { rejectWithValue }) => {
    try {
      const token = await axios.post(`${url}/customers/login`, {
        username: values.email,
        password: values.password,
      });
      localStorage.setItem("token", token.data);

      return token.data;
    } catch (error) {
      console.log(error.reponse.data);
      return rejectWithValue(error.reponse.data);
    }
  }
);
export const exitEmail = createAsyncThunk(
  "auth/exitEmail",
  async (values, { rejectWithValue }) => {
    try {
      const exit = await axios.get(`${url}/customers/email-exist/${values}`);
      return exit.data;
    } catch (error) {
      console.log(error.reponse.data);
      return rejectWithValue(error.reponse.data);
    }
  }
);

export const currentCustomer = createAsyncThunk(
  "customers/current-user",
  async (values, { rejectWithValue }) => {
    const token = localStorage.getItem("token");

    try {
      const currentuser = await axios.get(`${url}/customers/current-user`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      });
      return currentuser.data;
    } catch (error) {
      console.log(error.reponse.data);

      return rejectWithValue(error.reponse.data);
    }
  }
);

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loadUser(state, action) {
      const token = state.token;
      if (token) {
        const user = jwtDecode(token);
        return {
          ...state,
          token,
          email: user.email,
          userLoaded: true,
        };
      } else return { ...state, userLoaded: true };
    },
    logoutUser(state, action) {
      localStorage.removeItem("token");
      return {
        ...state,
        token: "",
        name: "",
        email: "",
        registerStatus: "",
        registerError: "",
        loginStatus: "",
        loginError: "",
      };
    },
  },
  extraReducers: (builder) => {
    builder.addCase(registerUser.pending, (state, action) => {
      return { ...state, registerStatus: "pending" };
    });
    builder.addCase(registerUser.fulfilled, (state, action) => {
      return {
        ...state,
        registerStatus: "succes",
      };
      // return { ...state, userLoaded: true };
    });
    builder.addCase(registerUser.rejected, (state, action) => {
      return {
        ...state,
        registerError: action.payload,
        registerStatus: "rejected",
      };
    });
    builder.addCase(loginUser.pending, (state, action) => {
      return {
        ...state,
        loginStatus: "pending",
      };
    });
    builder.addCase(loginUser.rejected, (state, action) => {
      return {
        ...state,
        loginStatus: "rejected",
        loginError: "Sai tài khoản hoặc mật khẩu",
      };
    });
    builder.addCase(loginUser.fulfilled, (state, action) => {
      if (action.payload) {
        const user = jwtDecode(action.payload);
        return {
          ...state,
          token: action.payload,
          email: user.sub,
          loginStatus: "success",
        };
      } else return state;
    });
    builder.addCase(exitEmail.pending, (state, action) => {
      return {
        ...state,
        emailStatus: false,
      };
    });
    builder.addCase(exitEmail.rejected, (state, action) => {
      return {
        ...state,
        emailStatus: false,
        emailError: action.payload,
      };
    });
    builder.addCase(exitEmail.fulfilled, (state, action) => {
      return {
        ...state,
        emailStatus: action.payload,
      };
    });
    builder.addCase(currentCustomer.pending, (state, action) => {
      return {
        ...state,
        currentStatus: "Cho",
      };
    });
    builder.addCase(currentCustomer.rejected, (state, action) => {
      toast.warning("Bạn chưa đăng nhập", {
        position: "top-right",
      });
      return {
        ...state,
        currentStatus: "That bai",
        currentError: action.payload,
      };
    });
    builder.addCase(currentCustomer.fulfilled, (state, action) => {
      if (action.payload === "token expired") {
        toast.warning("Phiên đăng nhập của bạn đã hết hạn", {
          position: "top-right",
        });
        return {
          ...state,
          currentStatus: "Hết hạn đăng nhập",
        };
      }
      return {
        ...state,
        user: action.payload,
        currentStatus: "Thanh cong",
      };
    });
  },
});
export default authSlice;
