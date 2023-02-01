import { configureStore } from "@reduxjs/toolkit";
import CartSlice from "../slices/CartSlices";
const store=configureStore({
    reducer:{
        todoCart: CartSlice.reducer,
    }
})
export default store;