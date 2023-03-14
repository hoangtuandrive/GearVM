import { createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
const initialState = {
  cartItems: localStorage.getItem("cartItems")
    ? JSON.parse(localStorage.getItem("cartItems"))
    : [],
  cartTotalQuantity: 0,
  cartTotalAmount: 0,
};

const CartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addTocart(state, action) {
      const existingIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );

      if (existingIndex >= 0) {
        state.cartItems[existingIndex] = {
          ...state.cartItems[existingIndex],
          cartQuantity: state.cartItems[existingIndex].cartQuantity + 1,
        };
        toast.info("Đã thêm sản phẩm vào giỏ hàng", {
          position: "top-right",
        });
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
    removeCart(state, action) {
      state.cartItems.map((cartItem) => {
        if (cartItem.id === action.payload.id) {
          const nextCartItems = state.cartItems.filter(
            (item) => item.id !== cartItem.id
          );
          state.cartItems = nextCartItems;
          toast.error("Đã xóa sản phẩm khỏi giỏ hàng", {
            position: "top-right",
          });
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
    OrderCart(state, action) {
      //  const cartItems= localStorage.getItem("cartItems");
      //  if(cartItems.)
    },
  },
});

export default CartSlice;
