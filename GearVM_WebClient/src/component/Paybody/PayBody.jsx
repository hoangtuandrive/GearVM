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
import { currentCustomer } from "../../redux/slices/AuthSlices";
import Table from "react-bootstrap/Table";
import CustomButon from "../Custom/CustomButon/CustomButon";
import { Button } from "antd";

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

  const handlePayOffline = () => {
    navigate("/payOffline", { replace: true });
  };

  const handlePay = (e) => {
    dispatch(currentCustomer());
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

    e.preventDefault();
  };

  return (
    <Container>
      <div className={cx("wrapPayBody")}>
        <div className={cx("wrapPayBody_right")}>
          <div className={cx("wrapPayBody_right_infoProduct")}>
            <h5 className={cx("Paytxt")}>Thông tin đơn hàng</h5>
            <div className={cx("wrapPayBody_right_infoProduct_wrap")}>
              {/* {cart.cartItems.map((item) => (
                <ItemCard key={item.id} data={item} />
              ))} */}
              <Table>
                <thead>
                  <tr>
                    <th>
                      <h5 className={cx("wrapListCart_Content_tr_text")}>
                        Tên sản phẩm
                      </h5>
                    </th>

                    <th>Đơn giá</th>
                    <th className={cx("listCart_total")}>Số lượng</th>
                    <th className={cx("listCart_total")}>Thành tiền</th>
                  </tr>
                </thead>
                {console.log(cartFilter)}
                {cartFilter.map((cartItem) => (
                  <TableCart key={cartItem.id} cartItem={cartItem} />
                ))}
              </Table>
            </div>
            <div className={cx("wrapPayBody_right_infoProduct_wrap_rep")}>
              {cartFilter.map((item) => (
                <ItemCard key={item.id} data={item} />
              ))}
            </div>
          </div>
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
                  Click={handlePay}
                  name="Thanh toán bằng thẻ Visa,Mastercard"
                />
              </div>

              <div>
                <CustomButon
                  Click={handlePayOffline}
                  name="Phương thức khác (Ngân hàng, Momo, COD)"
                />

                {/* <h5 style={{ color: "black" }}>Thanh toán khi nhận hàng</h5> */}
              </div>
            </div>
          </div>

          {/* <div className={cx("info-order-rep")}>
            <div className={cx("wrapPayBody_right_address")}>
              <h5>Thông tin nhận hàng</h5>
              <div
                className={cx("wrapPayBody_right_address_btn")}
                onClick={handleShow}
              >
                <h3>+</h3>
                <h5>Thêm Địa chỉ</h5>
              </div>
            </div>
            <div className={cx("wrapPayBody_right_Sumpay")}>
              <h5>Thanh toán</h5>
              <div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Tổng tạm tính:
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    75.768.000₫
                  </span>
                </div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Phí vận chuyển
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Miễn phí
                  </span>
                </div>
                <div className={cx("wrapPayBody_right_Sumpay_content")}>
                  <span className={cx("wrapPayBody_right_Sumpay_text")}>
                    Thành Tiền:
                  </span>
                  <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
                    75.768.000₫
                  </span>
                </div>
              </div>
              <input
                type="button"
                value="Thanh Toán"
                className={cx("wrapPayBody_right_Sumpay_btn")}
              />
            </div>
          </div> */}
        </div>
        {/* <div className={cx("wrapPayBody_left")}>
          <div className={cx("wrapPayBody_right_address")}>
            <h5>Thông tin nhận hàng</h5>
            <div
              className={cx("wrapPayBody_right_address_btn")}
              onClick={handleShow}
            >
              <h3>+</h3>
              <h5>Thêm Địa chỉ</h5>
            </div>
          </div>
          <div className={cx("wrapPayBody_right_Sumpay")}>
            <h5>Thanh toán</h5>
            <div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Tổng tạm tính:
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  75.768.000₫
                </span>
              </div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Phí vận chuyển
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Miễn phí
                </span>
              </div>
              <div className={cx("wrapPayBody_right_Sumpay_content")}>
                <span className={cx("wrapPayBody_right_Sumpay_text")}>
                  Thành Tiền:
                </span>
                <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
                  75.768.000₫
                </span>
              </div>
            </div>
            <input
              type="button"
              value="Thanh Toán"
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
