import React, { useEffect } from "react";
import styles from "./SumCard.module.scss";
import classNames from "classnames/bind";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import CartSlice from "../../redux/slices/CartSlices";
import CustomButon from "../Custom/CustomButon/CustomButon";

const cx = classNames.bind(styles);

const SumCard = () => {
  const dispatch = useDispatch();
  const cart = useSelector((state) => state.todoCart);

  useEffect(() => {
    dispatch(CartSlice.actions.totalCart());
  }, [cart, dispatch]);

  const navigate = useNavigate();

  const handelPageCart = () => {
    navigate("/cart", { replace: true });
  };
  return (
    <div className={cx("wrapSumCard")}>
      <div className={cx("wrapSumCard_Content")}>
        <h5>Tổng tiền({cart.cartItems.length}) sản phẩm</h5>
        <h5>
          {" "}
          {new Intl.NumberFormat("de-DE", {
            style: "currency",
            currency: "VND",
          }).format(cart.cartTotalAmount)}
        </h5>
      </div>
      <div className={cx("wrapSumCard_Content")}>
        {/* <input
          type="button"
          value="Xem Giỏ Hàng"
          className={cx("wrapSumCard_Button")}
          onClick={handelPageCart}
        /> */}
        <CustomButon Click={handelPageCart} name="Xem Giỏ Hàng" />
      </div>
    </div>
  );
};

export default SumCard;
