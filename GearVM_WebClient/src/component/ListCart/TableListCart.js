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

const cx = classNames.bind(styles);
const TableListCart = ({ cartItem }) => {
  const [quantity, setquantity] = useState(cartItem.cartQuantity);

  const [imgProduct, setImgProduct] = useState();
  const [disable, setDisable] = useState(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const cart = useSelector((state) => state.todoCart);
  // console.log(cart);
  useEffect(() => {
    getImage();
    dispatch(CartSlice.actions.totalCart());

    setquantity(cartItem.cartQuantity);
    if (cartItem.cartQuantity === 200) {
      setDisable(true);
    } else {
      setDisable(false);
    }
  }, [cart, dispatch, cartItem.cartQuantity]);

  const handleAddToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
  };
  const handleSubToCart = (product) => {
    dispatch(CartSlice.actions.subTocart(product));
  };
  const handleRemoveCart = (product) => {
    dispatch(CartSlice.actions.removeCart(product));
  };
  const handleChangeQuantity = (e) => {
    setquantity(e.target.value);
  };

  const handleChangeQuantityBlur = (e) => {
    if (e.target.value === "") {
      setquantity(cartItem.cartQuantity);
      const data = {
        id: cartItem.id,
        numberChange: cartItem.cartQuantity,
      };
      dispatch(CartSlice.actions.changeCart(data));
    } else if (e.target.value >= 200) {
      const data = {
        id: cartItem.id,
        numberChange: 200,
      };
      setquantity(200);
      dispatch(CartSlice.actions.changeCart(data));
    } else {
      const data = {
        id: cartItem.id,
        numberChange: e.target.value,
      };
      dispatch(CartSlice.actions.changeCart(data));
    }
  };
  async function getImage() {
    try {
      const response = await s3
        .getObject({
          Bucket: "gearvm",
          Key: cartItem.imageUri,
        })
        .promise();
      const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
        "base64"
      )}`;
      setImgProduct(imageSrc);
    } catch (error) {
      console.log(error);
    }
  }
  return (
    <tbody key={cartItem.id}>
      <tr>
        <td>
          <ToggleCheckbox cartItem={cartItem} />
        </td>
        <td className={cx("listCart_product_rep")}>
          <div className={cx("wrapListCart_Content_NameProduct")}>
            <div className=".k"></div>
            {/* <img
                  src="https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw"
                  className={cx("wrapListCart_Content_img")}
                /> */}
            <img src={imgProduct} className={cx("wrapListCart_Content_img")} />
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
                className={cx("wrapListCart_Content_quantity_btnsub")}
                onClick={() => handleSubToCart(cartItem)}
              >
                -
              </button>
              <input
                type="text"
                onBlur={handleChangeQuantityBlur}
                value={quantity}
                // defaultValue={cartItem.cartQuantity}
                onChange={handleChangeQuantity}
                className={cx("wrapListCart_Content_quantity_text")}
              />
              <button
                className={cx("wrapListCart_Content_quantity_btnadd")}
                onClick={() => handleAddToCart(cartItem)}
                disabled={disable}
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
          </div>
        </td>
        <td>
          <h5>
            {" "}
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(cartItem.price)}
          </h5>
          <h4 className={cx("wrapListCart_Content_Price")}>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(cartItem.price)}
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
              // value={cartItem.cartQuantity}
              onBlur={handleChangeQuantityBlur}
              value={quantity}
              // defaultValue={cartItem.cartQuantity}
              onChange={handleChangeQuantity}
              className={cx("wrapListCart_Content_quantity_text")}
            />
            <button
              className={cx("wrapListCart_Content_quantity_btnadd")}
              onClick={() => handleAddToCart(cartItem)}
              disabled={disable}
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
          <h5>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(cartItem.price * cartItem.cartQuantity)}
          </h5>
        </td>
      </tr>
      <tr className={cx("listCart_rep")}></tr>
    </tbody>
  );
};

export default TableListCart;
