import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { url } from "../../API/api";

const initialState = {
  orderStatus: "",
  orderError: "",
};
export const OrderCart = createAsyncThunk(
  "orders/place-order",
  async (values, { rejectWithValue }) => {
    try {
      const order = await axios.post(
        `${url}/orders/place-order`,
        {
          totalPrice: values.totalPrice,
          orderItems: values.orderItems,
        },
        // JSON.stringify(values),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json",
          },
        }
      );
      return order.data;
    } catch (error) {
      console.log(error.reponse.data);
      return rejectWithValue(error.reponse.data);
    }
  }
);
const orderSlices = createSlice({
  name: "order",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(OrderCart.pending, (state, action) => {
      return {
        ...state,
        orderStatus: "pending",
      };
    });
    builder.addCase(OrderCart.rejected, (state, action) => {
      return {
        ...state,
        orderStatus: "rejected",
        orderError: action.payload,
      };
    });
    builder.addCase(OrderCart.fulfilled, (state, action) => {
      if (action.payload) {
        return {
          ...state,
          orderStatus: "success",
        };
      } else return state;
    });
  },
});
export default orderSlices;
