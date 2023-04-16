import React, { useContext } from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/OrderManager.module.scss";
import classNames from "classnames/bind";
import Order from "../component/Order/Order";
import TrackingOrder from "../component/TrackingOrder/TrackingOrder";
import { AppContext } from "../component/context/AppProvider";
import ChatBox from "../component/chatBox/ChatBox";
import ScrolltoTop from "../component/Home/ScrolltoTop/ScrolltoTop";
const cx = classNames.bind(styles);
const OrderManger = () => {
  const { showChat } = useContext(AppContext);

  return (
    <div className={cx("wrapOrderManager")}>
      <Header />
      <Order />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      <Footer />
    </div>
  );
};

export default OrderManger;
