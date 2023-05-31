import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { url } from "../../API/api";

const initialState = {
  itemTracking: {},
  AllOrder: [],
  orderStatus: "",
  orderError: "",
  orderTrackingStatus: "",
  orderTrackingError: "",
  getAllOrderStatus: "",
  getAllOrderError: "",
  methodStatus: "",
  methodError: "",
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
            orderItemDtos: values.orderItems,
            code: values.code,
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
          console.log(response.data.id);
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

export const GetAllOrderSlice = createAsyncThunk(
  "orders/GetAll-order",
  async (values, { rejectWithValue }) => {
    try {
      const GetAllOrder = await axios.get(`${url}/orders/current-customer`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      });
      return GetAllOrder.data;
    } catch (error) {
      // console.log(error.response.data);
      // console.log(rejectWithValue(error));
      return rejectWithValue(error.response.data);
    }
  }
);

export const TrackingOrderSlice = createAsyncThunk(
  "orders/tracking-order",
  async (values, { rejectWithValue }) => {
    try {
      const TrackingOrder = await axios.get(`${url}/orders/${values}`, {
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

export const OrderMethod = createAsyncThunk(
  "orders/post-orderMethod",
  async (values, { rejectWithValue }) => {
    console.log(values.shippingDetailDto);
    try {
      const orderMethod = await axios.post(
        `${url}/orders/place-order-alt/${values.method}`,
        {
          totalPrice: values.totalPrice,
          shippingDetailDto: {
            name: values.shippingDetailDto.name,
            address: values.shippingDetailDto.address,
            phoneNumber: values.shippingDetailDto.phone,
            email: values.shippingDetailDto.email,
            shippingCost: values.shippingDetailDto.shippingCost,
          },
          // shippingDetailDto: values.shippingDetailDto,
          orderItemDtos: values.orderItems,
          code: values.code,
        },
        // JSON.stringify(values),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json",
          },
        }
      );
      console.log(orderMethod);
      return orderMethod.data;
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
    builder.addCase(GetAllOrderSlice.pending, (state, action) => {
      return {
        ...state,
        getAllOrderStatus: "pending",
      };
    });
    builder.addCase(GetAllOrderSlice.rejected, (state, action) => {
      return {
        ...state,
        getAllOrderStatus: "rejected",
        getAllOrderError: action.payload,
      };
    });
    builder.addCase(GetAllOrderSlice.fulfilled, (state, action) => {
      if (JSON.stringify(state.AllOrder) !== JSON.stringify(action.payload)) {
        state.AllOrder = action.payload;
      }
      state.getAllOrderStatus = "fulfilled";
    });
    builder.addCase(OrderMethod.pending, (state, action) => {
      return {
        ...state,
        methodStatus: "pending",
      };
    });
    builder.addCase(OrderMethod.rejected, (state, action) => {
      return {
        ...state,
        methodStatus: "rejected",
        methodError: action.payload,
      };
    });
    builder.addCase(OrderMethod.fulfilled, (state, action) => {
      state.methodStatus = "fulfilled";
    });
  },
});
export default orderSlices;
