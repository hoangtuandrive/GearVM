import React from "react";
import styles from "./ListCart.module.scss";
import classNames from "classnames/bind";
import Table from "react-bootstrap/Table";
import { useDispatch, useSelector } from "react-redux";
import CartSlice, { ChangeImg } from "../../redux/slices/CartSlices";
import { useState } from "react";
import { useEffect } from "react";
import ToggleCheckbox from "./ToggleCheckbox";
import axios from "axios";
import { OrderCart } from "../../redux/slices/OrderSlices";
import { Container, Col, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { s3 } from "../../aws";
import TableListCart from "./TableListCart";
import CustomButon from "../Custom/CustomButon/CustomButon";
const cx = classNames.bind(styles);
const ListCart = () => {
  const [quantity, setquantity] = useState(1);
  const [disabled, setdisable] = useState(false);
  const [imgProduct, setImgProduct] = useState([]);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const test = [];
  const cart = useSelector((state) => state.todoCart);

  const auth = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");
  // console.log(cart);

  useEffect(() => {
    dispatch(CartSlice.actions.totalCart());
    if (cart.cartTotalAmount === 0) {
      setdisable(true);
    } else {
      setdisable(false);
    }
  }, [cart, dispatch]);

  const handleSubmitToCart = (e) => {
    navigate("/pay", { replace: true });
  };
  const handleLogin = () => {
    navigate("/login", { replace: true });
  };

  return (
    <Container>
      <div className={cx("wrapListCart")}>
        <div className={cx("wrapListCart_Content")}>
          <Table>
            <thead>
              <tr>
                <th>Chọn</th>
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
            {cart.cartItems &&
              cart.cartItems.map((cartItem, index) => (
                <TableListCart key={cartItem.id} cartItem={cartItem} />
              ))}
          </Table>Pay
        </div>

        <div className={cx("listCart_Pay")}>
          <h6>Thanh Toán</h6>
          <div>
            <div className={cx("listCart_Pay_content")}>
              <span className={cx("listCart_Pay_content_text")}>
                Tổng tạm tính:
              </span>
              <span className={cx("listCart_Pay_content_text")}>
                {new Intl.NumberFormat("de-DE", {
                  style: "currency",
                  currency: "VND",
                }).format(cart.cartTotalAmount)}
              </span>
            </div>
            <div className={cx("listCart_Pay_content")}>
              <span className={cx("listCart_Pay_content_text")}>
                Thành Tiền:
              </span>
              <span className={cx("listCart_Pay_content_text_blue")}>
                {new Intl.NumberFormat("de-DE", {
                  style: "currency",
                  currency: "VND",
                }).format(cart.cartTotalAmount)}
              </span>
            </div>
          </div>
          {token ? (
            <div>
              {disabled ? (
                <input
                  disabled={disabled}
                  type="button"
                  value="Tiếp tục"
                  className={cx(`listCart_Pay_content_btn${disabled}`)}
                />
              ) : (
                <CustomButon Click={handleSubmitToCart} name="Tiếp tục" />
              )}
              {/* <input
                disabled={disabled}
                type="button"
                value="Tiếp tục"
                className={cx(`listCart_Pay_content_btn${disabled}`)}
                onClick={handleSubmitToCart}
              /> */}
            </div>
          ) : (
            <div>
              {/* <input
                type="button"
                value="Bạn cần đăng nhập để tiếp tục"
                className={cx("listCart_Pay_content_btn")}
                onClick={handleLogin}
              /> */}
              <CustomButon
                Click={handleLogin}
                name="Bạn cần đăng nhập để tiếp tục"
              />
            </div>
          )}
          {/* <input
            type="button"
            value="Thanh Toán"
            className={cx("listCart_Pay_content_btn")}
            onClick={handleSubmitToCart}
          /> */}
        </div>
      </div>
    </Container>
  );
};

export default ListCart;
