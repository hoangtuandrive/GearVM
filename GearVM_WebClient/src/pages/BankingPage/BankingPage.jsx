import React, { useContext, useEffect, useState } from "react";
import styles from "./BankingPage.module.scss";
import classNames from "classnames/bind";
import { Container, Row, Col } from "react-bootstrap";
import { Button } from "antd";
import {
  faCoins,
  faAddressCard,
  faUser,
  faArrowLeft,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { currentCustomer } from "../../redux/slices/AuthSlices";
import { OrderMethod } from "../../redux/slices/OrderSlices";
import CartSlice from "../../redux/slices/CartSlices";
import { AppContext } from "../../component/context/AppProvider";

const cx = classNames.bind(styles);

const BankingPage = () => {
  const navigate = useNavigate();
  // const handleComplete = () => {
  //   navigate("/payment-success");
  // };
  const handleBack = () => {
    navigate("/payOffline");
  };
  const { user, discount, setDiscount } = useContext(AppContext);
  const dispatch = useDispatch();
  const cart = useSelector((state) => state.todoCart);

  const JcartItems = localStorage.getItem("cartItems");
  const cartItems = JSON.parse(JcartItems);

  const [totalPrice, setTotalPrice] = useState(cart.cartTotalAmount);
  const percentDiscount = useSelector((state) => state.discount);
  const order = useSelector((state) => state.order);
  useEffect(() => {
    if (percentDiscount.percentDiscount > 0) {
      const totalPriceTinh =
        totalPrice - (totalPrice * percentDiscount.percentDiscount) / 100;
      setTotalPrice(totalPriceTinh);
    }
    // if (order.methodStatus == "fulfilled") {
    //   navigate("/payment-success");
    //   setDiscount("");
    // }
  }, [percentDiscount.percentDiscount, order.methodStatus]);

  const handleComplete = (e) => {
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
      orderItems,
      method: "BANK",
      shippingDetailDto: user,
      code: percentDiscount.discountCode,
    };

    dispatch(OrderMethod(cartOrder));

    cartItems.map((item) => {
      if (item.checkCart === true) {
        dispatch(CartSlice.actions.removeCartPay(item));
      }
    });

    navigate("/payment-success");
    setDiscount("");
    e.preventDefault();
  };
  return (
    <div className={cx("wrapMoMoPage")}>
      <div className={cx("wrapMoMoPage_about")}>
        <div className={cx("MomoPageContent")}>
          <div className={cx("BankingRow")}>
            <div className={cx("MomoPageContentLeft")}>
              <div>
                <div>
                  <h6 className={cx("Suptxt")}>Nhà cung cấp</h6>
                  <h4 className={cx("NameCty")}>
                    CÔNG TY THƯƠNG MẠI DỊCH VỤ GEARVM
                  </h4>
                </div>
                <hr style={{ color: "white" }} />
                <div>
                  <div className={cx("MomoTotal")}>
                    <FontAwesomeIcon
                      icon={faCoins}
                      style={{ color: "white" }}
                    />
                    <h6 className={cx("Totaltxt")}>Số tiền</h6>
                  </div>
                  <h4 className={cx("TotaltxtMagin")}>
                    {" "}
                    {new Intl.NumberFormat("de-DE", {
                      style: "currency",
                      currency: "VND",
                    }).format(totalPrice)}
                  </h4>
                </div>
                <hr style={{ color: "white" }} />
                <div>
                  <div className={cx("MomoTotal")}>
                    <FontAwesomeIcon
                      icon={faAddressCard}
                      style={{ color: "white" }}
                    />
                    <h6 className={cx("Totaltxt")}>Số Tài Khoản</h6>
                  </div>
                  <h4 className={cx("TotaltxtMagin")}>643704060075146</h4>
                </div>
                <hr style={{ color: "white" }} />
                <div>
                  <div className={cx("MomoTotal")}>
                    <FontAwesomeIcon icon={faUser} style={{ color: "white" }} />
                    <h6 className={cx("Totaltxt")}>Tên Tài Khoản</h6>
                  </div>
                  <h4 className={cx("TotaltxtMagin")}>Giang Vũ Hoàng Tuấn</h4>
                </div>
              </div>
              <div className={cx("btnBack")} onClick={handleBack}>
                <FontAwesomeIcon
                  icon={faArrowLeft}
                  style={{ color: "white", marginLeft: 5, fontWeight: "bold" }}
                />
                <h6 className={cx("btnBackTxt")}>Quay lại</h6>
              </div>
            </div>
            <div className={cx("wrapRight")}>
              <div className={cx("Back")}>
                <div className={cx("btnBack_Reposive")} onClick={handleBack}>
                  <FontAwesomeIcon
                    icon={faArrowLeft}
                    style={{
                      color: "white",
                      marginLeft: 5,
                      fontSize: 30,
                    }}
                  />
                </div>
                <h6 className={cx("btnBackTxt")}>Thanh Toán</h6>
                <h6></h6>
              </div>
              <div className={cx("MomoPageContentRight")}>
                <img
                  src={require("../../assets/logoGear.jpg")}
                  className={cx("imgLogo")}
                />
                <img
                  src={require("../../assets/LogoVib.jpg")}
                  className={cx("imgLogo")}
                />
              </div>
              <hr />
              {/* Open
              Reposive Trang Web Left */}
              <div className={cx("MomoPageContentLeft_Reposive")}>
                <div>
                  <div>
                    <h6 className={cx("Suptxt")}>Nhà cung cấp</h6>
                    <h4 className={cx("NameCty")}>
                      CÔNG TY THƯƠNG MẠI DỊCH VỤ GEARVM
                    </h4>
                  </div>
                  <hr style={{ color: "white" }} />
                  <div>
                    <div className={cx("MomoTotal")}>
                      <FontAwesomeIcon
                        icon={faCoins}
                        style={{ color: "white" }}
                      />
                      <h6 className={cx("Totaltxt")}>Số tiền</h6>
                    </div>
                    <h4 className={cx("TotaltxtMagin")}>
                      {" "}
                      {new Intl.NumberFormat("de-DE", {
                        style: "currency",
                        currency: "VND",
                      }).format(cart.cartTotalAmount)}
                    </h4>
                  </div>
                  <hr style={{ color: "white" }} />
                  <div>
                    <div className={cx("MomoTotal")}>
                      <FontAwesomeIcon
                        icon={faAddressCard}
                        style={{ color: "white" }}
                      />
                      <h6 className={cx("Totaltxt")}>Số Tài Khoản</h6>
                    </div>
                    <h4 className={cx("TotaltxtMagin")}>643704060075146</h4>
                  </div>
                  <hr style={{ color: "white" }} />
                  <div>
                    <div className={cx("MomoTotal")}>
                      <FontAwesomeIcon
                        icon={faUser}
                        style={{ color: "white" }}
                      />
                      <h6 className={cx("Totaltxt")}>Tên Tài Khoản</h6>
                    </div>
                    <h4 className={cx("TotaltxtMagin")}>Giang Vũ Hoàng Tuấn</h4>
                  </div>
                </div>
              </div>
              {/* Close 
              Reposive Trang Web Left */}

              <div className={cx("ScanCotent")}>
                <div className={cx("Scantxt")}>Quét Mã Để Thanh Toán</div>
                <div className={cx("QrCode")}>
                  <img
                    src={require("../../assets/QrVib.jpg")}
                    className={cx("imgQr")}
                  />
                </div>
                <div className={cx("Intro")}>
                  <h6 className={cx("Introtxt")}>
                    Sử dụng App ngân hàng hoặc ứng dụng Camera hỗ trợ QR code để
                    quét mã
                  </h6>
                </div>
                <div className={cx("Momobtn")}>
                  <button
                    className={cx("btnComplete")}
                    onClick={handleComplete}
                  >
                    {" "}
                    Hoàn Thành Thanh Toán
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BankingPage;
