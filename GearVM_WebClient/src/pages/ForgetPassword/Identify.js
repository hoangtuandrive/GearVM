import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "../login/login.scss";
import { useDispatch, useSelector } from "react-redux";
import { checkToken } from "../../redux/slices/AuthSlices";
const cx = classNames.bind(styles);

const Identify = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");

  const [otp, setOtp] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleOTP = () => {
    if (otp === "") {
      setErrorMessage("Bạn chưa nhập Email");
      return false;
    } else {
      setErrorMessage("");
      return true;
    }
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (handleOTP(otp) === true) {
      dispatch(checkToken(otp));
    }
  };
  useEffect(() => {
    if (auth.identifyStatus === "Thành Công") {
      navigate(`/resetpassword?token=${otp}`);
    }
  }, [auth, navigate]);
  return (
    <div className={cx("wrapLogin")}>
      {/* <Header /> */}
      <div className={cx("LoginContent")}>
        <form action="" id="form-login">
          <h1 className={cx("logintxt")}>Xác Nhận Mã OTP</h1>
          <div className={cx("form-group")}>
            <input
              type="text"
              placeholder="Otp"
              className={cx("form-input")}
              onChange={(e) => setOtp(e.target.value)}
              onBlur={handleOTP}
            />
          </div>
          {errorMessage === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage}</span>
          )}

          <input
            type="submit"
            value={
              auth.identifyStatus === "pending" ? "Submitting..." : "Tiếp tục"
            }
            className={cx("form-submit")}
            onClick={handleSubmit}
          />
          {auth.identifyStatus === "Từ chối" ? (
            <p className={cx("txtError")}>{auth.identiftError}</p>
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

export default Identify;
