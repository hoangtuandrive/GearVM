import React, { useState, useContext } from "react";
import classNames from "classnames/bind";
import styles from "./InfoCustomer.module.scss";
import { Container } from "react-bootstrap";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { AppContext } from "../context/AppProvider";

const cx = classNames.bind(styles);

const InfoCustomer = () => {
  const { user, setUser, errorMessage, setErrorMessage } =
    useContext(AppContext);
  const CurrentUser = useSelector((state) => state.auth.user);

  // const [errorMessage, setErrorMessage] = useState({
  //   messageName: "",
  //   messageAddress: "",
  //   messagePhone: "",
  //   messageEmail: "",
  // });

  const handlePhone = () => {
    const regexPhone = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    if (user.phone === "") {
      setErrorMessage({
        ...errorMessage,
        messagePhone: "Bạn chưa nhập số điện thoại",
      });
      return false;
    } else if (!regexPhone.test(user.phone)) {
      setErrorMessage({
        ...errorMessage,
        messagePhone: "Nhập số điện thoại sai định dạng",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messagePhone: "" });
      return true;
    }
  };

  const handleExitEmail = () => {
    const regexEmail =
      /^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$/;

    if (user.email === "") {
      setErrorMessage({ ...errorMessage, messageEmail: "Bạn chưa nhập Email" });
      return false;
    } else if (!regexEmail.test(user.email)) {
      setErrorMessage({
        ...errorMessage,
        messageEmail: "Nhập email sai định dạng",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageEmail: "" });
      return true;
    }
  };

  const handleName = () => {
    const regexName = /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/;
    if (user.name === "") {
      setErrorMessage({ ...errorMessage, messageName: "Bạn chưa nhập Họ tên" });
      return false;
    } else if (!regexName.test(user.name)) {
      setErrorMessage({
        ...errorMessage,

        messageName:
          "Tên người dùng phải có 3-16 ký tự và không được bao gồm bất kỳ ký tự đặc biệt nào!",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageName: "" });
      return true;
    }
  };

  const handleAddress = () => {
    // console.log(user.address === "");
    if (user.address === "") {
      setErrorMessage({
        ...errorMessage,
        messageAddress: "Bạn chưa nhập Địa chỉ",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageAddress: "" });
      return true;
    }
  };

  return (
    <Container className={cx("wrapPayBody")}>
      <div className={cx("Wrap_address")}>
        <h5 className={cx("Paytxt")}>Thông tin nhận hàng</h5>
        <div className={cx("address_btn")}>
          <label className={cx("input")}>
            <input
              className={cx("input__field")}
              type="text"
              placeholder=" "
              defaultValue={user.name}
              onChange={(e) => setUser({ ...user, name: e.target.value })}
              onBlur={handleName}
            />
            <span className={cx("input__label")}>Nhập tên người nhận</span>
            <span className={cx("input__label_improtant")}>*</span>
          </label>
          {errorMessage.messageName === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageName}</span>
          )}

          <label className={cx("input")}>
            <input
              className={cx("input__field")}
              type="text"
              placeholder=" "
              defaultValue={user.address}
              onChange={(e) => setUser({ ...user, address: e.target.value })}
              onBlur={handleAddress}
            />
            <span className={cx("input__label")}>Nhập địa chỉ</span>
            <span className={cx("input__label_improtant")}>*</span>
          </label>
          {errorMessage.messageAddress === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageAddress}</span>
          )}
          <label className={cx("input")}>
            <input
              className={cx("input__field")}
              type="text"
              placeholder=" "
              defaultValue={user.phone}
              onChange={(e) => setUser({ ...user, phone: e.target.value })}
              onBlur={handlePhone}
            />
            <span className={cx("input__label")}>Nhập số điện thoại</span>
            <span className={cx("input__label_improtant")}>*</span>
          </label>
          {errorMessage.messagePhone === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messagePhone}</span>
          )}
          <label className={cx("input")}>
            <input
              className={cx("input__field")}
              type="text"
              placeholder=" "
              defaultValue={user.email}
              onChange={(e) => setUser({ ...user, email: e.target.value })}
              onBlur={handleExitEmail}
            />
            <span className={cx("input__label")}>Nhập email</span>
          </label>
          {errorMessage.messageEmail === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageEmail}</span>
          )}
        </div>
      </div>
    </Container>
  );
};

export default InfoCustomer;
