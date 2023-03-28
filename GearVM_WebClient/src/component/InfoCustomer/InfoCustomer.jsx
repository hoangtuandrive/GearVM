import React from "react";
import classNames from "classnames/bind";
import styles from "./InfoCustomer.module.scss";
import { Container } from "react-bootstrap";

const cx = classNames.bind(styles);

const InfoCustomer = () => {
  return (
    <Container>
      <div className={cx("Wrap_address")}>
        <h5>Thông tin nhận hàng</h5>
        <div className={cx("address_btn")}>
          <label className={cx("input")}>
            <input className={cx("input__field")} type="text" placeholder=" " />
            <span className={cx("input__label")}>Nhập địa chỉ</span>
          </label>

          <label className={cx("input")}>
            <input className={cx("input__field")} type="text" placeholder=" " />
            <span className={cx("input__label")}>Nhập tên người nhận</span>
          </label>
          <label className={cx("input")}>
            <input className={cx("input__field")} type="text" placeholder=" " />
            <span className={cx("input__label")}>Nhập email</span>
          </label>

          <label className={cx("input")}>
            <input className={cx("input__field")} type="text" placeholder=" " />
            <span className={cx("input__label")}>Nhập số điện thoại</span>
          </label>
        </div>
      </div>
    </Container>
  );
};

export default InfoCustomer;
