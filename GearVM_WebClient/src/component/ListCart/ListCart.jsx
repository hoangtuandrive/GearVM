import React from "react";
import styles from "./ListCart.module.scss";
import classNames from "classnames/bind";
import Table from "react-bootstrap/Table";
import { useDispatch, useSelector } from "react-redux";
import CartSlice from "../../redux/slices/CartSlices";
import { useState } from "react";
import { useEffect } from "react";
import ToggleCheckbox from "./ToggleCheckbox";
import axios from "axios";
import { OrderCart } from "../../redux/slices/OrderSlices";
import { Container, Col, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const cx = classNames.bind(styles);
const ListCart = () => {
  const [quantity, setquantity] = useState(1);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const cart = useSelector((state) => state.todoCart);

  const auth = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");
  // console.log(cart);

  useEffect(() => {
    dispatch(CartSlice.actions.totalCart());
  }, [cart, dispatch]);

  const handleAddToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
  };
  const handleSubToCart = (product) => {
    dispatch(CartSlice.actions.subTocart(product));
  };
  const handleRemoveCart = (product) => {
    dispatch(CartSlice.actions.removeCart(product));
  };
  const handleChangeQuantity = (value) => {
    setquantity(value);
  };
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
              cart.cartItems.map((cartItem) => (
                <tbody key={cartItem.id}>
                  <tr>
                    <td>
                      <ToggleCheckbox cartItem={cartItem} />
                    </td>
                    <td className={cx("listCart_product_rep")}>
                      <div className={cx("wrapListCart_Content_NameProduct")}>
                        <img
                          src="https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw"
                          className={cx("wrapListCart_Content_img")}
                        />
                        <div className={cx(" .listCart_product_rep")}>
                          <h5 className={cx("wrapListCart_Content_Name")}>
                            {cartItem.name}
                          </h5>
                          {/* <h4 className={cx('wrapListCart_Content_noice')}>Chỉ còn 2 sản phẩm</h4> */}
                        </div>
                      </div>
                      <div className={cx("listCart_rep")}>
                        <div className={cx("wrapListCart_Content_quantity")}>
                          <button
                            className={cx(
                              "wrapListCart_Content_quantity_btnsub"
                            )}
                            onClick={() => handleSubToCart(cartItem)}
                          >
                            -
                          </button>
                          <input
                            type="text"
                            value={cartItem.cartQuantity}
                            onChange={handleChangeQuantity}
                            className={cx("wrapListCart_Content_quantity_text")}
                          />
                          <button
                            className={cx(
                              "wrapListCart_Content_quantity_btnadd"
                            )}
                            onClick={() => handleAddToCart(cartItem)}
                          >
                            +
                          </button>
                        </div>
                        <div>
                          <button
                            className={cx(
                              "wrapListCart_Content_quantity_remove"
                            )}
                            onClick={() => handleRemoveCart(cartItem)}
                          >
                            Xóa
                          </button>
                        </div>
                      </div>
                    </td>
                    <td>
                      <h5>30.889.000đ</h5>
                      <h4 className={cx("wrapListCart_Content_Price")}>
                        {cartItem.price}đ
                      </h4>

                      <h5 className={cx("listCart_total_txt")}>
                        {cartItem.price * cartItem.cartQuantity}đ
                      </h5>
                    </td>
                    <td className={cx("listCart_total")}>
                      <div className={cx("wrapListCart_Content_quantity")}>
                        <button
                          className={cx("wrapListCart_Content_quantity_btnsub")}
                          onClick={() => handleSubToCart(cartItem)}
                        >
                          -
                        </button>
                        <input
                          type="text"
                          value={cartItem.cartQuantity}
                          onChange={handleChangeQuantity}
                          className={cx("wrapListCart_Content_quantity_text")}
                        />
                        <button
                          className={cx("wrapListCart_Content_quantity_btnadd")}
                          onClick={() => handleAddToCart(cartItem)}
                        >
                          +
                        </button>
                      </div>
                      <div>
                        <button
                          className={cx("wrapListCart_Content_quantity_remove")}
                          onClick={() => handleRemoveCart(cartItem)}
                        >
                          Xóa
                        </button>
                      </div>
                    </td>
                    {/* Thành Tiền */}
                    <td className={cx("listCart_total")}>
                      <h5>{cartItem.price * cartItem.cartQuantity}đ</h5>
                    </td>
                  </tr>
                  <tr className={cx("listCart_rep")}></tr>
                </tbody>
              ))}
          </Table>
        </div>

        <div className={cx("listCart_Pay")}>
          <h6>Thanh Toán</h6>
          <div>
            <div className={cx("listCart_Pay_content")}>
              <span className={cx("listCart_Pay_content_text")}>
                Tổng tạm tính:
              </span>
              <span className={cx("listCart_Pay_content_text")}>
                {cart.cartTotalAmount}₫
              </span>
            </div>
            <div className={cx("listCart_Pay_content")}>
              <span className={cx("listCart_Pay_content_text")}>
                Thành Tiền:
              </span>
              <span className={cx("listCart_Pay_content_text_blue")}>
                {cart.cartTotalAmount}₫
              </span>
            </div>
          </div>
          {token ? (
            <input
              type="button"
              value="Tiếp tục"
              className={cx("listCart_Pay_content_btn")}
              onClick={handleSubmitToCart}
            />
          ) : (
            <div>
              <input
                type="button"
                value="Bạn cần đăng nhập để tiếp tục"
                className={cx("listCart_Pay_content_btn")}
                onClick={handleLogin}
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
