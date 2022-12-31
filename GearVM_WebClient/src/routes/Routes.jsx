import Home from "../pages/home/Home";
import Product from "../pages/Product";
import Card from '../pages/Card'


const publicRoutes = [
  { path: "/", component: Home },
  { path: "/product", component: Product },
  { path: "/card", component: Card },

];

const privateRoutes = [];

export { publicRoutes, privateRoutes };