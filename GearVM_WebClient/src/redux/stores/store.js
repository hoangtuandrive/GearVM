import { configureStore } from "@reduxjs/toolkit";

import authSlice from "../slices/AuthSlices";
import CartSlice from "../slices/CartSlices";
import orderSlices from "../slices/OrderSlices";

// const persistConfig = {
//   key: "root",
//   storage,
// };
// const rootReducer = {
//   todoCart: CartSlice.reducer,
//   auth: authSlice.reducer,
//   order: orderSlices.reducer,
// };
// const persistedReducer = persistReducer(persistConfig, rootReducer);
const store = configureStore({
  reducer: {
    todoCart: CartSlice.reducer,
    auth: authSlice.reducer,
    order: orderSlices.reducer,
  },
});

export default store;
