import React, { useContext, useEffect, useState } from "react";
import styles from "./PayBody.module.scss";
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

const cx = classNames.bind(styles);
const PayBody = () => {
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
    axios
      .post(`${url}/payment/create-payment-link`)
      .then((response) => {
        if (response.data) {
          window.location.href = response.data;
        }
        console.log(response.data);
      })
      .catch((err) => console.log(err.message));
    e.preventDefault();

    let orderItems = [];

    cartItems.map((item) => {
      if (item.checkCart === true) {
        // console.log(item);
        let price = item.price;
        let quantity = item.cartQuantity;
        let productId = item.id;
        let orderItemTemp = { price, quantity, productId };
        orderItems.push(orderItemTemp);
      }
      // console.log(oderItem);
    });
    // let totalPrice =;
    const cartOrder = {
      totalPrice: cart.cartTotalAmount,
      orderItems,
    };
    dispatch(OrderCart(cartOrder));
    window.addEventListener("unload", function () {
      cartItems.map((item) => {
        if (item.checkCart === true) {
          dispatch(CartSlice.actions.removeCart(item));
        }
      });
    });
  };

  return (
    <Container>
      <div className={cx("wrapPayBody")}>
        <div className={cx("wrapPayBody_right")}>
          <div className={cx("wrapPayBody_right_infoProduct")}>
            <h5>Th??ng tin ????n h??ng</h5>
            <div className={cx("wrapPayBody_right_infoProduct_wrap")}>
              {/* {cart.cartItems.map((item) => (
                <ItemCard key={item.id} data={item} />
              ))} */}

              <TableCart data={cartFilter} />
            </div>
            <div className={cx("wrapPayBody_right_infoProduct_wrap_rep")}>
              {cartFilter.map((item) => (
                <ItemCard key={item.id} data={item} />
              ))}
            </div>
          </div>
          {/* <div className={cx("wrapPayBody_right_address")}>
            <h5>M?? gi???m gi??</h5>
            <Input placeholder="nh???p m?? gi???m gi??" />
          </div> */}
          <div className={cx("wrapPayBody_right_metodpay")}>
            <h5>Ph????ng th???c thanh to??n</h5>
            <div className={cx("wrapPayBody_right_metodpay_wrapbtn")}>
              <div
                className={cx("wrapPayBody_right_address_btn")}
                onClick={handlePay}
              >
                <h5 style={{ color: "black" }}>Thanh to??n VNPAY-QR</h5>
                <h6>
                  Thanh to??n qua Internet Banking, Visa, Master, JCB, VNPAY-QR
                </h6>
              </div>
              <div className={cx("wrapPayBody_right_address_btn")}>
                <h5 style={{ color: "black" }}>Thanh to??n khi nh???n h??ng</h5>
              </div>
            </div>
          </div>
          <div className={cx("info-order-rep")}>
            <div className={cx("wrapPayBody_right_address")}>
              <h5>Th??ng tin nh???n h??ng</h5>
              <div
                className={cx("wrapPayBody_right_address_btn")}
                onClick={handleShow}
              >
                <h3>+</h3>
                <h5>Th??m ?????a ch???</h5>
              </div>
            </div>
            {/* <div className={cx("wrapPayBody_right_Sumpay")}>
              <h5>Thanh to??n</h5>
              <div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    T???ng t???m t??nh:
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    75.768.000???
                  </span>
                </div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Ph?? v???n chuy???n
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Mi???n ph??
                  </span>
                </div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Th??nh Ti???n:
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
                    75.768.000???
                  </span>
                </div>
              </div>
              <input
                type="button"
                value="Thanh To??n"
                className={cx("wrapPayBody_right_Sumpay_btn")}
              />
            </div> */}
          </div>
        </div>
        {/* <div className={cx("wrapPayBody_left")}>
          <div className={cx("wrapPayBody_right_address")}>
            <h5>Th??ng tin nh???n h??ng</h5>
            <div
              className={cx("wrapPayBody_right_address_btn")}
              onClick={handleShow}
            >
              <h3>+</h3>
              <h5>Th??m ?????a ch???</h5>
            </div>
          </div>
          <div className={cx("wrapPayBody_right_Sumpay")}>
            <h5>Thanh to??n</h5>
            <div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  T???ng t???m t??nh:
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  75.768.000???
                </span>
              </div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Ph?? v???n chuy???n
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Mi???n ph??
                </span>
              </div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Th??nh Ti???n:
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
                  75.768.000???
                </span>
              </div>
            </div>
            <input
              type="button"
              value="Thanh To??n"
              className={cx("wrapPayBody_right_Sumpay_btn")}
              onClick={ChangePayment}
            />
          </div>
        </div> */}

        <ModalAddress />
      </div>
    </Container>
  );
};

export default PayBody;
