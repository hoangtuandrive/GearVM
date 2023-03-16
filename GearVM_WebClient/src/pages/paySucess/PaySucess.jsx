import React from "react";
import { Container } from "react-bootstrap";
import Footer from "../../component/Home/footer/Footer";
import Header from "../../component/Home/header/Header";
import styles from "./PaySucess.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);
const PaySucess = () => {
  return (
    <div className={cx("wrapPaysucess")}>
      <Header />
      <Container className={cx("wrapContentPay")}>
        <div className={cx("ContentPay")}>
          <img
            src={require("../../assets/checksucess.jpg")}
            style={{ width: 80 }}
          />
          <h5>Thanh toán thành công</h5>
          <div className={cx("InfoPay")}>
            Mã số đơn hàng là :<p className={cx("txtID")}>12345</p>
          </div>
          <div className={cx("InfoPay")}>
            Bạn có thể xem chi tiết trong{" "}
            <p className={cx("txtPaySucess")}>thông tin đơn hàng</p>
          </div>

          <div className={cx("wrapSumCard_Content")}>
            <input
              type="button"
              value="Tiếp tục mua hàng"
              className={cx("wrapSumCard_Button")}
              //   onClick={handelPageCart}
            />
          </div>
        </div>
      </Container>
      <Footer />
    </div>
  );
};

export default PaySucess;
