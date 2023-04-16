import React, { useContext } from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import ProductView from "../component/productDetail/ProductView";
import styles from "../sass/Cart.module.scss";
import classNames from "classnames/bind";
import ListCart from "../component/ListCart/ListCart";
import { AppContext } from "../component/context/AppProvider";
import ChatBox from "../component/chatBox/ChatBox";
import ScrolltoTop from "../component/Home/ScrolltoTop/ScrolltoTop";
const cx = classNames.bind(styles);
const Cart = () => {
  const { showChat } = useContext(AppContext);
  return (
    <div className={cx("wrapCart")}>
      <Header />
      <ListCart />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      <Footer />
    </div>
  );
};

export default Cart;
