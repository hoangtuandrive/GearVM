import React from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/Pay.module.scss";
import classNames from "classnames/bind";
import PayBody from "../component/Paybody/PayBody";
import InfoCustomer from "../component/InfoCustomer/InfoCustomer";

const cx = classNames.bind(styles);
const PayOffline = () => {
  return (
    <div className={cx("wrapPay")}>
      <Header />
      <InfoCustomer />
      <PayBody name="Thanh toán chuyển khoản" />
      <Footer />
    </div>
  );
};

export default PayOffline;
