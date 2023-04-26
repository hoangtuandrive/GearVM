import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "../login/login.scss";
import { useDispatch, useSelector } from "react-redux";
import { forgotPassword, loginUser } from "../../redux/slices/AuthSlices";
import Header from "../../component/Home/header/Header";

const cx = classNames.bind(styles);
const ForgetPassword = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");

  const [user, setUser] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(user);
    if (handleExitEmail(user) === true) {
      console.log(123);
      dispatch(forgotPassword(user));
    }
  };
  const handleExitEmail = () => {
    const regexEmail =
      /^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$/;

    if (user === "") {
      setErrorMessage("Bạn chưa nhập Email");
      return false;
    } else if (!regexEmail.test(user)) {
      console.log(123);
      setErrorMessage("Nhập email sai định dạng");
      return false;
    } else {
      setErrorMessage("");
      return true;
    }
  };
  useEffect(() => {
    if (auth.forgotStatus === "Thành Công") {
      navigate("/identify");
    }
  }, [auth, navigate]);

  return (
    <div className={cx("wrapLogin")}>
      {/* <Header /> */}
      <div className={cx("LoginContent")}>
        <form action="" id="form-login">
          <h1 className={cx("logintxt")}>Quên Mật Khẩu</h1>
          <div className={cx("form-group")}>
            <input
              type="text"
              placeholder="Email"
              className={cx("form-input")}
              onChange={(e) => setUser(e.target.value)}
              onBlur={handleExitEmail}
            />
          </div>
          {errorMessage === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage}</span>
          )}
          {auth.forgotStatus === "Từ chối" ? (
            <p className={cx("txtError")}>{auth.forgotError}</p>
          ) : null}
          <input
            type="submit"
            value={
              auth.forgotStatus === "pending" ? "Submitting..." : "Tiếp tục"
            }
            className={cx("form-submit")}
            onClick={handleSubmit}
          />

          <div className="addtional-link">
            <Link to="/" className={cx("txtLogin")}>
              Trang chủ
            </Link>
            <Link to="/login" className={cx("txtLogin")}>
              Đăng nhập
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

export default ForgetPassword;
