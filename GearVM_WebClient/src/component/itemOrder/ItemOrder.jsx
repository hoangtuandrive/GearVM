import React, { useEffect, useState } from "react";
import styles from "./ItemOrder.module.scss";
import classNames from "classnames/bind";
import { s3 } from "../../aws";

const cx = classNames.bind(styles);

const ItemOrder = ({ data }) => {
  const [imageData, setImageData] = useState();

  useEffect(() => {
    getImage();
  }, []);

  async function getImage() {
    try {
      const response = await s3
        .getObject({
          Bucket: "gearvm",
          Key: data.product.imageUri,
        })
        .promise();
      const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
        "base64"
      )}`;

      setImageData(imageSrc);
    } catch (error) {
      // console.log(error);
    }
  }

  return (
    <figure className={cx("itemside")}>
      <div className={cx("aside")}>
        <img
          src={imageData ? imageData : ""}
          className={cx("img-sm", "border")}
        />
      </div>
      <figcaption className={cx("info align-self-center")}>
        <p className={cx("title")}>
          {data.product.name} <br /> {data.quantity} sản phẩm
        </p>{" "}
        <span className={cx("text-muted")}>
          {" "}
          {new Intl.NumberFormat("de-DE", {
            style: "currency",
            currency: "VND",
          }).format(data.price * data.quantity)}{" "}
        </span>
      </figcaption>
    </figure>
  );
};

export default ItemOrder;
