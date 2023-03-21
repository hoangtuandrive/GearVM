import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { s3 } from "../../aws";
const initialState = {
  cartItems: localStorage.getItem("cartItems")
    ? JSON.parse(localStorage.getItem("cartItems"))
    : [],
  cartTotalQuantity: 0,
  cartTotalAmount: 0,
  ImgAnh: [],
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
    ChangeImgUri(state, action) {
      let cartTemp = [];

      // async function getImage(item) {
      //   try {
      //     const response = await s3
      //       .getObject({
      //         Bucket: "gearvm",
      //         Key: item.imageUri,
      //       })
      //       .promise();
      //     const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
      //       "base64"
      //     )}`;

      //     return imageSrc;
      //   } catch (error) {
      //     // console.log(error);
      //     return error;
      //   }
      // }

      // state.cartItems.map((item) => {
      //   getImage(item)
      //     .then((data) => {
      //       console.log(item);
      //       let ChangeImg = {
      //         // ...item,
      //         imageUri: data,
      //       };
      //       return ChangeImg;
      //     })
      //     .then((ChangeImg) => {
      //       cartTemp.push(ChangeImg);
      //     });
      // });
      // console.log(cartTemp);
      // localStorage.setItem("cartItems", ChangeImg);
    },
  },
  extraReducers: (builder) => {
    builder.addCase(ChangeImg.fulfilled, (state, action) => {
      state.ImgAnh = action.payload;
      // console.log(action.payload);
      // state.cartItems = [
      //   ...state.cartItems,
      //   {
      //     imageUri: action.payload,
      //   },
      //   //
      // ];
      // return {
      //   // ...state,
      //   // ImgAnh: action.payload,
      //   // ImgAnh: [...state.ImgAnh, action.payload],
      // };
    });
  },
});

export default CartSlice;
