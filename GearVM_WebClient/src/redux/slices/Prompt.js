import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { toast } from "react-toastify";
import { url } from "../../API/api";

const initialState = {
  message: "",
  promptStatus: "",
  promptError: "",
};
export const GetPrompt = createAsyncThunk(
  "prompt/Getpropmt",
  async (values, { rejectWithValue }) => {
    try {
      const promptData = await axios.get(`${url}/prompts/get-prompt`, {
        params: {
          question: values.message,
        },
      });
      return promptData.data;
    } catch (error) {
      console.log(error.response.data);
      return rejectWithValue(error.response.data);
    }
  }
);

const promptSlice = createSlice({
  name: "prompt",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(GetPrompt.pending, (state, action) => {
      return { ...state, promptStatus: "pending", message: action.payload };
    });
    builder.addCase(GetPrompt.rejected, (state, action) => {
      return {
        ...state,
        promptError: action.payload,
        promptStatus: "rejected",
      };
    });
    builder.addCase(GetPrompt.fulfilled, (state, action) => {
      return {
        ...state,
        promptStatus: "succes",
        message: action.payload,
      };
      // return { ...state, userLoaded: true };
    });
  },
});
export default promptSlice;
