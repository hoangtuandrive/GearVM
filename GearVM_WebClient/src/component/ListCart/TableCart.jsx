import React, { useEffect, useState } from "react";
import styles from "./ListCart.module.scss";
import classNames from "classnames/bind";
import { useDispatch, useSelector } from "react-redux";
import Table from "react-bootstrap/Table";
import { s3 } from "../../aws";

const cx = classNames.bind(styles);

const TableCart = ({ cartItem }) => {
  const [imgProduct, setImgProduct] = useState();

  const dispatch = useDispatch();

  const cart = useSelector((state) => state.todoCart);

  useEffect(() => {
    getImage();
  }, []);
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
        <td className={cx("listCart_product_rep")}>
          <div className={cx("wrapListCart_Content_NameProduct")}>
            <img src={imgProduct} className={cx("wrapListCart_Content_img")} />
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
          {/* <h4 className={cx("wrapListCart_Content_Price")}>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(cartItem.price)}
          </h4> */}

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
  );
};

export default TableCart;
