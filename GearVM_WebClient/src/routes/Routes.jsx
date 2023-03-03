import Home from "../pages/home/Home";
import ProductDetail from "../pages/ProductDetail";
import Cart from '../pages/Cart'
import Catalog from "../pages/Catalog";
import Pay from "../pages/Pay";
import Login from "../pages/login/Login";
import Resign from "../pages/resign/Register";
import OrderManger from "../pages/OrderManger";
import GuidePayment from "../pages/guidePayment/GuidePayment";
import GuidePolicy from "../pages/guidePolicy/GuidePolicy";
import GuideDeli from "../pages/guidedeli/GuideDeli";
import Promotion from "../pages/promotion/Promotion";
const publicRoutes = [
  { path: "/", component: Home },
  { path: "/productdetail", component: ProductDetail },
  { path: "/cart", component: Cart },
  { path: "/catalog", component: Catalog },
  { path: "/pay", component: Pay },
  { path: "/login", component: Login },
  { path: "/resign", component: Resign },
  { path: "/orderManager", component: OrderManger },
  { path: "/guidePayment", component: GuidePayment  },
  { path: "/guidePolicy", component: GuidePolicy  },
  { path: "/guideDeli", component: GuideDeli  },
  { path: "/promotion", component: Promotion  },
  

];

const privateRoutes = [];

export { publicRoutes, privateRoutes };