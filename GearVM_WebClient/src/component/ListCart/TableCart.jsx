import React from "react";
import styles from "./ListCart.module.scss";
import classNames from "classnames/bind";
import { useDispatch, useSelector } from "react-redux";
import Table from "react-bootstrap/Table";

const cx = classNames.bind(styles);

const TableCart = ({ data }) => {
  const dispatch = useDispatch();

  const cart = useSelector((state) => state.todoCart);

  return (
    <div className={cx("wrapListCart_Content")}>
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
        {data &&
          data.map((cartItem) => (
            <tbody key={cartItem.id}>
              <tr>
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
                  <div>
                    <h4>{cartItem.cartQuantity}</h4>
                  </div>
                </td>
                {/* Thành Tiền */}
                <td className={cx("listCart_total")}>
                  <h5>
                    {" "}
                    {new Intl.NumberFormat("de-DE", {
                      style: "currency",
                      currency: "VND",
                    }).format(cartItem.price * cartItem.cartQuantity)}
                  </h5>
                </td>
              </tr>
            </tbody>
          ))}
      </Table>
    </div>
  );
};

export default TableCart;
