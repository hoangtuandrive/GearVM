import React, { useEffect, useState } from "react";
import styles from "./ItemCard.module.scss";
import classNames from "classnames/bind";
import { s3 } from "../../aws";
const cx = classNames.bind(styles);
const ItemCard = ({ data }) => {
  const [imgProduct, setImgProduct] = useState();

  useEffect(() => {
    getImage();
  }, []);
  async function getImage() {
    try {
      const response = await s3
        .getObject({
          Bucket: "gearvm",
          Key: data.imageUri,
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
  // console.log(data);
  return (
    <div className={cx("wrapItemCard")}>
      <img className={cx("ItemCard_Img")} src={imgProduct} />
      <div className={cx("ItemCard_Content")}>
        <h6 className={cx("txt_ItemCard_Content_Name")}>{data.name}</h6>
        <h6 className={cx("txt_ItemCard_Content_quantity")}>
          số lượng: {data.cartQuantity}
        </h6>
        <h6 className={cx("txt_ItemCard_Content_price")}>
          {new Intl.NumberFormat("de-DE", {
            style: "currency",
            currency: "VND",
          }).format(data.price * data.cartQuantity)}
        </h6>
      </div>
    </div>
  );
};

export default ItemCard;
