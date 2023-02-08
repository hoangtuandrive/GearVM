import { configureStore } from "@reduxjs/toolkit";
import authSlice from "../slices/AuthSlices";

import CartSlice from "../slices/CartSlices";
const store=configureStore({
    reducer:{
        todoCart: CartSlice.reducer,
        auth:  authSlice.reducer,
    }
})
export default store;