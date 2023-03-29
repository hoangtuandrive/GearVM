import React from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/OrderManager.module.scss";
import classNames from "classnames/bind";
import Order from "../component/Order/Order";
import TrackingOrder from "../component/TrackingOrder/TrackingOrder";

const cx = classNames.bind(styles);
const OrderManger = () => {
  return (
    <div className={cx("wrapOrderManager")}>
      <Header />
      <Order />

      <Footer />
    </div>
  );
};

export default OrderManger;
