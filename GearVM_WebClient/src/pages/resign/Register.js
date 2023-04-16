import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "../login/login.scss";
import { useDispatch, useSelector } from "react-redux";
import {
  registerUser,
  loginUser,
  exitEmail,
} from "../../redux/slices/AuthSlices";
import { useEffect } from "react";
import { DatePicker } from "antd";
import dayjs from "dayjs";
const cx = classNames.bind(styles);

const Register = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);
  const [user, setUser] = useState({
    name: "",
    dateOfBirth: "",
    gender: "MALE",
    phoneNumber: "",
    email: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState({
    messageEmail: "",
    messagePass: "",
    messagePhone: "",
    messageName: "",
  });
  // console.log(auth);
  useEffect(() => {
    if (auth.registerStatus === "succes") {
      dispatch(loginUser(user));
      navigate("/cart");
    }
  }, [auth.registerStatus]);
  const handleSubmit = (e) => {
    e.preventDefault();
    // console.log(user);
    if (
      handleName(user.email) === true &&
      handlePasswrod(user.password) === true &&
      handlePhone(user.phoneNumber) === true &&
      handleExitEmail(user.email) === true
    ) {
      dispatch(registerUser(user));
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
    } else if (auth.emailStatus === true) {
      setErrorMessage({
        ...errorMessage,
        messageEmail: "Email đã tồn tại vui lòng nhập lại email khác",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageEmail: "" });
      return true;
    }
  };
  const handlePhone = () => {
    const regexPhone = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    if (user.phoneNumber === "") {
      setErrorMessage({
        ...errorMessage,
        messagePhone: "Bạn chưa nhập số điện thoại",
      });
      return false;
    } else if (!regexPhone.test(user.phoneNumber)) {
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
          "Mật khẩu nên có 8-20 ký tự và bao gồm ít nhất 1 chữ cái, 1 số",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messagePass: "" });
      return true;
    }
  };
  const dateFormatList = ["DD/MM/YYYY", "DD/MM/YY", "DD-MM-YYYY", "DD-MM-YY"];
  // console.log(user.dateOfBirth);
  return (
    <div className={cx("wrapLogin")}>
      <div className={cx("LoginContent")}>
        <form action="" id="form-login">
          <h1 className={cx("logintxt")}>Đăng Ký</h1>

          <div className={cx("form-group")}>
            <input
              type="text"
              placeholder="Họ và Tên"
              className={cx("form-input")}
              onChange={(e) => setUser({ ...user, name: e.target.value })}
              onBlur={handleName}
            />
          </div>
          {errorMessage.messageName === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageName}</span>
          )}
          <div className={cx("form-group")}>
            {/* <DatePicker
              format={dateFormatList}
              defaultValue={dateFormatList[0]}
              className={cx("form-input")}
              // value={dayjs}
              onChange={(value) => {
                console.log(dayjs().date());
                if (value !== null) {
                  const date = `${value.$y}-${value.$M}-${value.$d.date()}`;
                  setUser({ ...user, dateOfBirth: date });
                }
              }}
            /> */}

            <input
              type="date"
              // placeholder="YYYY-MM-DD"
              // data-date-format="DD MMMM YYYY"
              // placeholder="dateOfBirth"
              defaultValue={dateFormatList[0]}
              format={dateFormatList}
              className={cx("form-input")}
              onChange={(e) =>
                setUser({ ...user, dateOfBirth: e.target.value })
              }
            />
          </div>

          <div className={cx("form-group")}>
            <input
              type="text"
              placeholder="Email"
              className={cx("form-input")}
              onChange={(e) => {
                setUser({ ...user, email: e.target.value });
                dispatch(exitEmail(e.target.value));
              }}
              onBlur={handleExitEmail}
            />
          </div>
          {errorMessage.messageEmail === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messageEmail}</span>
          )}
          <div className={cx("form-group")}>
            <input
              type="phone"
              placeholder="Số điện thoại"
              className={cx("form-input")}
              onChange={(e) =>
                setUser({ ...user, phoneNumber: e.target.value })
              }
              onBlur={handlePhone}
            />
          </div>
          {errorMessage.messagePhone === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messagePhone}</span>
          )}

          <div className={cx("form-check-group")}>
            <div className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name="gioiTinh"
                id="Nam"
                checked={user.gender === "MALE" ? true : false}
                onChange={(e) => setUser({ ...user, gender: "MALE" })}
              />
              <label className="form-check-label" htmlFor="Nam">
                Nam
              </label>
            </div>
            <div className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name="gioiTinh"
                id="Nu"
                checked={user.gender === "FEMALE" ? true : false}
                onChange={(e) => {
                  setUser({ ...user, gender: "FEMALE" });
                }}
              />
              <label className="form-check-label" htmlFor="Nu">
                Nữ
              </label>
            </div>
            <div className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name="gioiTinh"
                id="Khac"
                checked={user.gender === "UNDEFINED" ? true : false}
                onChange={(e) => setUser({ ...user, gender: "UNDEFINED" })}
              />
              <label className="form-check-label" htmlFor="Khac">
                Khác
              </label>
            </div>
          </div>
          <div className={cx("form-group")}>
            <input
              type="password"
              placeholder="Mật khẩu"
              className={cx("form-input")}
              onChange={(e) => setUser({ ...user, password: e.target.value })}
              onBlur={handlePasswrod}
              autoComplete="on"
            />
          </div>
          {errorMessage.messagePass === "" ? null : (
            <span style={{ color: "red" }}>{errorMessage.messagePass}</span>
          )}

          <input
            type="submit"
            value="Đăng ký"
            className={cx("form-submit")}
            onClick={handleSubmit}
          />
          <div className="addtional-link">
            <Link to="/" className={cx("txtLogin")}>
              Trang chủ
            </Link>
            <Link to="/login" className={cx("txtLogin")}>
              Đăng Nhập
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

export default Register;
