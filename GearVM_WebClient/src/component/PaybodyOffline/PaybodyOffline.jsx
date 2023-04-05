import React, { useContext, useEffect, useState } from "react";
import styles from "../Paybody/PayBody.module.scss";
import classNames from "classnames/bind";
import ItemCard from "../itemCard/ItemCard";
import ModalAddress from "../ModalAddress/ModalAddress";
import { AppContext } from "../context/AppProvider";
// import { Input } from "antd";
import { useSelector, useDispatch } from "react-redux";
import { Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TableCart from "../ListCart/TableCart";
import CartSlice from "../../redux/slices/CartSlices";

import { OrderCart } from "../../redux/slices/OrderSlices";
import axios from "axios";
import { url } from "../../API/api";
import { currentCustomer } from "../../redux/slices/AuthSlices";
import Table from "react-bootstrap/Table";
import CustomButon from "../Custom/CustomButon/CustomButon";
import { Button } from "antd";

const cx = classNames.bind(styles);
const PaybodyOffline = ({ name }) => {
  const navigate = useNavigate();

  const dispatch = useDispatch();

  const { setShow } = useContext(AppContext);

  const [cartFilter, setCartFilter] = useState([]);

  const cart = useSelector((state) => state.todoCart);

  const JcartItems = localStorage.getItem("cartItems");
  const cartItems = JSON.parse(JcartItems);

  const FilterCartTrue = () => {
    let CartTrue = [];
    cartItems.map((item) => {
      if (item.checkCart === true) {
        CartTrue.push(item);
      }
      return CartTrue;
    });
    setCartFilter(CartTrue);
  };

  useEffect(() => {
    FilterCartTrue();
  }, []);

  const handleShow = () => {
    setShow(true);
  };

  // const ChangePayment = () => {
  //   navigate("/payment", { replace: true });
  // };
  // console.log(cartFilter);
  const handlePay = (e) => {
    navigate("/moMopage", { replace: true });
  };

  const handleBanking = () => {
    navigate("/bankingPage", { replace: true });
  };

  const handleCash = () => {
    navigate("/cashPage", { replace: true });
  };

  const [user, setUser] = useState({
    name: "",
    address: "",
    phone: "",
    email: "",
  });
  const [errorMessage, setErrorMessage] = useState({
    messageName: "",
    messageAddress: "",
    messagePhone: "",
    messageEmail: "",
  });
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
    if (user.address === "") {
      setErrorMessage({
        ...errorMessage,
        messageAddress: "Bạn chưa nhập Địa chỉ",
      });
      return false;
    } else {
      setErrorMessage({ ...errorMessage, messageName: "" });
      return true;
    }
  };

  return (
    <Container>
      {/* <div className={cx("wrapPayBody")}> */}
      <div className={cx("wrapPayBody_right")}>
        <div className={cx("wrapPayBody_right_address")}>
          <h5 className={cx("Paytxt")}>Mã giảm giá</h5>
          <div className={cx("discout")}>
            <label className={cx("input")}>
              <input
                className={cx("input__field")}
                type="text"
                placeholder=" "
              />
              <span className={cx("input__label")}>Nhập mã khuyến mãi</span>
            </label>
            <div className={cx("btn", "btn_Use")}>Áp Dụng</div>
          </div>
        </div>
        <div className={cx("wrapPayBody_right_Sumpay_content")}>
          <span className={cx("wrapPayBody_right_Sumpay_text")}>
            Tổng tiền:
          </span>
          <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(cart.cartTotalAmount)}
          </span>
        </div>
        <div className={cx("wrapPayBody_right_metodpay")}>
          <h5 className={cx("Paytxt")}>Phương thức thanh toán</h5>
          <div className={cx("wrapPayBody_right_metodpay_wrapbtn")}>
            {/* <div
                className={cx("wrapPayBody_right_address_btn")}
                onClick={handlePay}
              >
                <h5 style={{ color: "black" }}>Thanh toán VNPAY-QR</h5>
                <h6>
                  Thanh toán qua Internet Banking, Visa, Master, JCB, VNPAY-QR
                </h6>
              </div> */}
            <div>
              <CustomButon
                Click={handleBanking}
                name={"Thanh toán qua ngân hàng"}
              />
            </div>

            <div>
              <CustomButon name="Thanh toán tiền mặt" Click={handleCash} />

              {/* <h5 style={{ color: "black" }}>Thanh toán khi nhận hàng</h5> */}
            </div>
            <div>
              <CustomButon Click={handlePay} name={"Thanh toán MoMo"} />
            </div>
          </div>
        </div>
      </div>

      <ModalAddress />
      {/* </div> */}
    </Container>
  );
};

export default PaybodyOffline;
