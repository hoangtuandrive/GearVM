import Home from "../pages/home/Home";
import ProductDetail from "../pages/ProductDetail";
import Cart from '../pages/Cart'
import Catalog from "../pages/Catalog";

const publicRoutes = [
  { path: "/", component: Home },
  { path: "/productdetail", component: ProductDetail },
  { path: "/cart", component: Cart },
  { path: "/catalog", component: Catalog },


];

const privateRoutes = [];

export { publicRoutes, privateRoutes };