import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { s3 } from "../../aws";
import axios from "axios";
import { url } from "../../API/api";
import { useNavigate } from "react-router-dom";
const initialState = {
  cartItems: localStorage.getItem("cartItems")
    ? JSON.parse(localStorage.getItem("cartItems"))
    : [],
  cartTotalQuantity: 0,
  cartTotalAmount: 0,
  ImgAnh: [],
  cartQuantityChecked: [],
  statusCheck: "",
  errorCheck: "",
};

export const ChangeImg = createAsyncThunk(
  "Cart/ChangeImg",
  async (values, { rejectWithValue }) => {
    try {
      const response = await s3
        .getObject({
          Bucket: "gearvm",
          Key: values.imageUri,
        })
        .promise();
      const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
        "base64"
      )}`;

      return imageSrc;
    } catch (error) {
      // console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);
export const CheckQuantity = createAsyncThunk(
  "Cart/CheckQuantity",
  async (values, { rejectWithValue }) => {
    try {
      const cart = JSON.parse(localStorage.getItem("cartItems"));
      const cartTemp = cart.map((item) => ({
        id: item.id,
        cartQuantity: item.cartQuantity,
      }));

      const CheckQuantity = await axios.post(
        `${url}/products/check-cart-quantity`,
        cartTemp,
        // { data: cartTemp },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      return CheckQuantity.data;
    } catch (error) {
      console.log(error);
      // console.log(rejectWithValue(error));
      return rejectWithValue(error.response.data);
    }
  }
);

const CartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addTocart(state, action) {
      const existingIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );

      if (existingIndex >= 0) {
        if (state.cartItems[existingIndex].cartQuantity >= 201) {
          toast.info(
            "Bạn chỉ có thể mua tối đa 200 sản phẩm cho cùng loại sản phẩm",
            {
              position: "top-right",
            }
          );
        } else {
          state.cartItems[existingIndex] = {
            ...state.cartItems[existingIndex],
            cartQuantity: state.cartItems[existingIndex].cartQuantity + 1,
          };
          toast.info("Đã thêm sản phẩm vào giỏ hàng", {
            position: "top-right",
          });
        }
      } else {
        let tempProductItem = {
          ...action.payload,
          cartQuantity: 1,
          checkCart: true,
        };
        state.cartItems.push(tempProductItem);
        toast.success("Tăng thêm 1 sản phẩm", {
          position: "top-right",
        });
      }
      localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
    },
    subTocart(state, action) {
      const itemIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );

      if (state.cartItems[itemIndex].cartQuantity > 1) {
        state.cartItems[itemIndex].cartQuantity -= 1;

        toast.info("Giảm xuống 1 sản phẩm", {
          position: "top-right",
        });
      } else if (state.cartItems[itemIndex].cartQuantity === 1) {
        const nextCartItems = state.cartItems.filter(
          (item) => item.id !== action.payload.id
        );
        state.cartItems = nextCartItems;
        toast.error("Sản phẩm đã xóa khỏi giỏ hàng", {
          position: "top-right",
        });
      }

      localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
    },
    changeCart(state, action) {
      const itemIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );
      if (action.payload.numberChange <= 0) {
        const nextCartItems = state.cartItems.filter(
          (item) => item.id !== action.payload.id
        );
        state.cartItems = nextCartItems;
      } else {
        state.cartItems[itemIndex].cartQuantity = parseInt(
          action.payload.numberChange
        );
      }

      localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
    },
    removeCart(state, action) {
      state.cartItems.map((cartItem) => {
        if (cartItem.id === action.payload.id) {
          const nextCartItems = state.cartItems.filter(
            (item) => item.id !== cartItem.id
          );
          state.cartItems = nextCartItems;
          // toast.error("Will close after 15s", { autoClose: 15000 });
          toast.error("Đã xóa sản phẩm khỏi giỏ hàng", {
            position: "top-right",
          });
        }

        localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
        return state;
      });
    },
    removeCartPay(state, action) {
      state.cartItems.map((cartItem) => {
        if (cartItem.id === action.payload.id) {
          const nextCartItems = state.cartItems.filter(
            (item) => item.id !== cartItem.id
          );
          state.cartItems = nextCartItems;
        }

        localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
        return state;
      });
    },
    clearCart(state, action) {
      state.cartItems = [];
      localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
      toast.error("Xóa tất cả sản phẩm khỏi giỏ hàng", {
        position: "top-right",
      });
    },
    totalCart(state, action) {
      const nextCartItems = state.cartItems.filter(
        (item) => item.checkCart === true
      );
      let { total, quantity } = nextCartItems.reduce(
        (cartTotal, cartItem) => {
          const { price, cartQuantity } = cartItem;
          const itemTotal = price * cartQuantity;
          cartTotal.total += itemTotal;
          cartTotal.quantity += cartQuantity;
          return cartTotal;
        },
        {
          total: 0,
          quantity: 0,
        }
      );
      total = parseFloat(total.toFixed(2));
      state.cartTotalQuantity = quantity;
      state.cartTotalAmount = total;
    },
    ChangeCheckCart(state, action) {
      const itemIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );
      if (state.cartItems[itemIndex].checkCart === true) {
        state.cartItems[itemIndex].checkCart = false;
        toast.success("Đã bỏ chọn sản phẩm", {
          position: "top-right",
        });
      } else {
        state.cartItems[itemIndex].checkCart = true;
        toast.success("Đã chọn lại sản phẩm", {
          position: "top-right",
        });
      }
      localStorage.setItem("cartItems", JSON.stringify(state.cartItems));
    },
  },
  extraReducers: (builder) => {
    builder.addCase(CheckQuantity.pending, (state, action) => {
      console.log(action.payload);
      return {
        ...state,
        statusCheck: "pending",
      };
    });
    builder.addCase(CheckQuantity.rejected, (state, action) => {
      return {
        ...state,
        statusCheck: "rejected",
        errorCheck: action.payload,
      };
    });
    builder.addCase(CheckQuantity.fulfilled, (state, action) => {
      // const navigate = useNavigate();
      const cart = JSON.parse(localStorage.getItem("cartItems"));
      const cartChecked = cart.filter(
        (item) => !action.payload.includes(item.id)
      );
      const aString = cart.map(JSON.stringify);
      const bString = cartChecked.map(JSON.stringify);

      const isEqual =
        aString.length === bString.length &&
        aString.every((value, index) => value === bString[index]);
      console.log(cartChecked);
      console.log(isEqual);
      if (!isEqual) {
        toast.error(
          "Xóa 1 số sản phẩm đã hết hàng",
          { autoClose: 15000 },
          {
            position: "top-center",
          }
        );
        localStorage.setItem("cartItems", JSON.stringify(cartChecked));
        return {
          ...state,
          statusCheck: "fulfilled",
          cartItems: cartChecked,
          cartQuantityChecked: action.payload,
        };
      } else {
        // navigate("/pay");
        return {
          ...state,
          statusCheck: "fulfilled",
          cartQuantityChecked: action.payload,
        };
      }
      // if (cartChecked) console.log(cartChecked);
    });
  },
});

export default CartSlice;
