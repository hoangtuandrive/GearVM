import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { url } from "../../API/api";

const initialState = {
  percentDiscount: 0,
  discountStatus: "",
  discountError: "",
};

export const DiscountCode = createAsyncThunk(
  "discount/discount-code",
  async (values, { rejectWithValue }) => {
    try {
      const discount = await axios.get(
        `${url}/discount/discount-code/${values}`
        // JSON.stringify(values),
      );
      return discount.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);

const discountSlices = createSlice({
  name: "disount",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(DiscountCode.pending, (state, action) => {
      return {
        ...state,
        discountStatus: "pending",
      };
    });
    builder.addCase(DiscountCode.rejected, (state, action) => {
      return {
        ...state,
        discountStatus: "rejected",
        discountError: action.payload,
      };
    });
    builder.addCase(DiscountCode.fulfilled, (state, action) => {
      if (action.payload) {
        state.percentDiscount = action.payload;
        state.discountStatus = "success";
      }
    });
  },
});
export default discountSlices;
