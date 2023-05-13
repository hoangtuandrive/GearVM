import React, { useContext, useEffect } from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/Pay.module.scss";
import classNames from "classnames/bind";

import InfoCustomer from "../component/InfoCustomer/InfoCustomer";
import PaybodyOffline from "../component/PaybodyOffline/PaybodyOffline";
import { AppContext } from "../component/context/AppProvider";
import ChatBox from "../component/chatBox/ChatBox";
import ScrolltoTop from "../component/Home/ScrolltoTop/ScrolltoTop";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
const cx = classNames.bind(styles);
const PayOffline = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const cart = useSelector((state) => state.todoCart);

  const JcartItems = localStorage.getItem("cartItems");
  const cartItems = JSON.parse(JcartItems);
  useEffect(() => {
    const CarTrue = cartItems.map((item) => {
      if (item.checkCart === true) {
        return item.cartTotalAmount;
      }
    });
    if (token && CarTrue) {
    } else {
      navigate("/", { replace: true });
    }
  }, [token]);
  const { showChat } = useContext(AppContext);
  return (
    <div className={cx("wrapPay")}>
      <Header />
      <InfoCustomer />
      <PaybodyOffline name="Thanh toán chuyển khoản" />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      <Footer />
    </div>
  );
};

export default PayOffline;
