import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "../login/login.scss";
import { useDispatch, useSelector } from "react-redux";
import { resetPassword } from "../../redux/slices/AuthSlices";

const cx = classNames.bind(styles);

const ResetPassword = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");

  const [user, setUser] = useState({
    rePassword: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState({
    messageRePass: "",
    messagePass: "",
  });
  const handlePasswrod = () => {
    const regexPasswrod =
      /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
    if (user.password === "") {
      setErrorMessage({
        ...errorMessage,
        messagePass: "Bạn chưa nhập mật khẩu",
      });
      return false;
    } else if (!regexPasswrod.test(user.password)) {
      setErrorMessage({
        ...errorMessage,
        messagePass:
          "Mật khẩu nên có 8-20 ký tự và bao gồm ít nhất 1 chữ cái, 1 số!",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messagePass: "" });
      return true;
    }
  };
  const handleRePasswrod = () => {
    const regexPasswrod =
      /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
    if (user.rePassword === "") {
      setErrorMessage({
        ...errorMessage,
        messageRePass: "Bạn chưa nhập mật khẩu",
      });
      return false;
    } else if (!regexPasswrod.test(user.rePassword)) {
      setErrorMessage({
        ...errorMessage,
        messageRePass:
          "Mật khẩu nên có 8-20 ký tự và bao gồm ít nhất 1 chữ cái, 1 số!",
      });
      return false;
    } else if (user.password != user.rePassword) {
      setErrorMessage({
        ...errorMessage,
        messageRePass: "Password không giống nhau",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageRePass: "" });
      return true;
    }
  };
  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const tokens = query.get("token");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (handlePasswrod(user) === true && handleRePasswrod(user) === true) {
      console.log(123);
      const value = { token: tokens, password: user.password };
      dispatch(resetPassword(value));
    }
  };
  useEffect(() => {
    if (auth.resetPassStatus === "Thành Công") {
      navigate("/login");
    }
  }, [auth, navigate]);

  return (
    <div className={cx("wrapLogin")}>
      {/* <Header /> */}
      <div className={cx("LoginContent")}>
        <form action="" id="form-login">
          <h1 className={cx("logintxt")}>Đặt Lại Mật Khẩu</h1>

          <div className={cx("form-group")}>
            <input
              type="password"
              placeholder="Mật khẩu mới"
              className={cx("form-input")}
              onChange={(e) => setUser({ ...user, password: e.target.value })}
              onBlur={handlePasswrod}
              autoComplete="on"
            />
          </div>
          {errorMessage.messagePass === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messagePass}</span>
          )}

          <div className={cx("form-group")}>
            <input
              type="password"
              placeholder="Xác nhận mật khẩu mới"
              className={cx("form-input")}
              onChange={(e) => setUser({ ...user, rePassword: e.target.value })}
              onBlur={handleRePasswrod}
              autoComplete="on"
            />
          </div>
          {errorMessage.messageRePass === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageRePass}</span>
          )}

          <input
            type="submit"
            value={
              auth.resetPassStatus === "pending" ? "Submitting..." : "Tiếp tục"
            }
            className={cx("form-submit")}
            onClick={handleSubmit}
          />
          {auth.resetPassStatus === "Từ chối" ? (
            <p className={cx("txtError")}>{auth.resetPassError}</p>
          ) : null}
          <div className="addtional-link">
            <Link to="/" className={cx("txtLogin")}>
              Trang chủ
            </Link>
            <Link to="/resign" className={cx("txtLogin")}>
              Đăng ký
            </Link>
            {/* <Link to="/forgotpassword">Forgot Password?</Link> */}
          </div>
        </form>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
      <div className={cx("wrapdot")}>
        <span className={cx("dot")}></span>
      </div>
    </div>
  );
};

export default ResetPassword;
