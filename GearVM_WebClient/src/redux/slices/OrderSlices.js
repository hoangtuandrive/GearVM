import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { url } from "../../API/api";

const initialState = {
  itemTracking: {},
  orderStatus: "",
  orderError: "",
  orderTrackingStatus: "",
  orderTrackingError: "",
};

export const OrderCart = createAsyncThunk(
  "orders/place-order",
  async (values, { rejectWithValue }) => {
    try {
      const order = await axios
        .post(
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
        )
        .then(async (response) => {
          await axios
            .post(`${url}/payment/create-payment-link/`, {
              id: response.data.id,
              totalPrice: response.data.totalPrice,
            })
            .then((res) => {
              if (res.data) {
                window.location.href = res.data;
              }
              console.log(res.data);
            })
            .catch((err) => console.log(err.message));
        });
      return order.data;
    } catch (error) {
      console.log(error.reponse.data);
      return rejectWithValue(error.reponse.data);
    }
  }
);

export const TrackingOrderSlice = createAsyncThunk(
  "orders/tracking-order",
  async (values, { rejectWithValue }) => {
    try {
      const TrackingOrder = await axios.get(`${url}/orders/1`, {
        // headers: {
        //   Authorization: `Bearer ${localStorage.getItem("token")}`,
        //   "Content-Type": "application/json",
        // },
      });
      return TrackingOrder.data;
    } catch (error) {
      // console.log(error.response.data);
      // console.log(rejectWithValue(error));
      return rejectWithValue(error.response.data);
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
    builder.addCase(TrackingOrderSlice.pending, (state, action) => {
      return {
        ...state,
        orderTrackingStatus: "pending",
      };
    });
    builder.addCase(TrackingOrderSlice.rejected, (state, action) => {
      return {
        ...state,
        orderTrackingStatus: "rejected",
        orderError: action.payload,
      };
    });
    builder.addCase(TrackingOrderSlice.fulfilled, (state, action) => {
      return {
        ...state,
        orderTrackingStatus: "fulfilled",
        itemTracking: action.payload,
      };
    });
  },
});
export default orderSlices;
