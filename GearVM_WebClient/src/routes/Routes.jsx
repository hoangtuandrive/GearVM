import Home from "../pages/home/Home";
import Product from "../pages/Product";
import Card from '../pages/Card'
import Catalog from "../pages/Catalog";

const publicRoutes = [
  { path: "/", component: Home },
  { path: "/product", component: Product },
  { path: "/card", component: Card },
  { path: "/catalog", component: Catalog },


];

const privateRoutes = [];

export { publicRoutes, privateRoutes };