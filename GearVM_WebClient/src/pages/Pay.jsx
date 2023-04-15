import React, { useContext, useEffect } from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/Pay.module.scss";
import classNames from "classnames/bind";
import PayBody from "../component/Paybody/PayBody";
import { AppContext } from "../component/context/AppProvider";
import { useSelector } from "react-redux";

import ChatBox from "../component/chatBox/ChatBox";
import ScrolltoTop from "../component/Home/ScrolltoTop/ScrolltoTop";
const cx = classNames.bind(styles);
const Pay = () => {
  const { setUser, showChat } = useContext(AppContext);

  const CurrentUser = useSelector((state) => state.auth.user);

  useEffect(() => {
    setUser({
      name: CurrentUser.name,
      address: CurrentUser.address,
      phone: CurrentUser.phoneNumber,
      email: CurrentUser.email,
    });
  }, [CurrentUser]);
  return (
    <div className={cx("wrapPay")}>
      <Header />
      <PayBody />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      <Footer />
    </div>
  );
};

export default Pay;
