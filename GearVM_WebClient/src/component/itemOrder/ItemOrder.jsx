import React from "react";
import styles from "./ItemOrder.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);

const ItemOrder = ({ data }) => {
  return (
    <figure className={cx("itemside")}>
      <div className={cx("aside")}>
        <img
          src="https://i.imgur.com/iDwDQ4o.png"
          className={cx("img-sm", "border")}
        />
      </div>
      <figcaption className={cx("info align-self-center")}>
        <p className={cx("title")}>
          {data.name} <br /> {data.amout} sản phẩm
        </p>{" "}
        <span className={cx("text-muted")}>
          {" "}
          {new Intl.NumberFormat("de-DE", {
            style: "currency",
            currency: "VND",
          }).format(data.price)}{" "}
        </span>
      </figcaption>
    </figure>
  );
};

export default ItemOrder;
