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
import { OrderMethod } from "../../redux/slices/OrderSlices";

const cx = classNames.bind(styles);
const PaybodyOffline = ({ name }) => {
  const navigate = useNavigate();

  const dispatch = useDispatch();

  const { setShow, discount, setDiscount } = useContext(AppContext);

  const [cartFilter, setCartFilter] = useState([]);

  const cart = useSelector((state) => state.todoCart);

  const JcartItems = localStorage.getItem("cartItems");
  const cartItems = JSON.parse(JcartItems);

  const order = useSelector((state) => state.order);
  const [totalPrice, setTotalPrice] = useState(cart.cartTotalAmount);
  const percentDiscount = useSelector((state) => state.discount);

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
    if (percentDiscount.percentDiscount > 0) {
      const totalPriceTinh =
        totalPrice - (totalPrice * percentDiscount.percentDiscount) / 100;
      setTotalPrice(totalPriceTinh);
    }
    // if (order.methodStatus == "fulfilled") {
    //   navigate("/cashPage", { replace: true });
    //   setDiscount("");
    // }
  }, [percentDiscount.percentDiscount, order.methodStatus]);

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

  const { user } = useContext(AppContext);

  const handleCash = (e) => {
    dispatch(currentCustomer());
    let orderItems = [];

    cartItems.map((item) => {
      if (item.checkCart === true) {
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
      totalPrice: totalPrice,
      shippingDetailDto: user,
      orderItems,
      code: percentDiscount.discountCode,
      method: "COD",
    };

    dispatch(OrderMethod(cartOrder));

    cartItems.map((item) => {
      if (item.checkCart === true) {
        dispatch(CartSlice.actions.removeCartPay(item));
      }
    });

    navigate("/cashPage", { replace: true });
    setDiscount("");

    e.preventDefault();
  };

  return (
    <Container>
      {/* <div className={cx("wrapPayBody")}> */}
      <div className={cx("wrapPayBody_right")}>
        <div className={cx("wrapPayBody_right_Sumpay_content")}>
          <span className={cx("wrapPayBody_right_Sumpay_text")}>
            Tổng tiền:
          </span>
          <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(totalPrice)}
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
            <div className={cx("btnPay")}>
              <CustomButon
                Click={handleBanking}
                name={"Thanh toán qua ngân hàng"}
              />
            </div>

            <div className={cx("btnPay")}>
              <CustomButon Click={handlePay} name={"Thanh toán MoMo"} />
            </div>
            <div className={cx("btnPay")}>
              <CustomButon name="Thanh toán tiền mặt" Click={handleCash} />

              {/* <h5 style={{ color: "black" }}>Thanh toán khi nhận hàng</h5> */}
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
