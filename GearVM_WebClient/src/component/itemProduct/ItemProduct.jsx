import React, { useState, useEffect } from "react";
import classNames from "classnames/bind";
import styles from "./ItemProduct.modulo.scss";
import { Image } from "antd";
import { s3 } from "../../aws";
import Customfigure from "../Custom/Customfigure/Customfigure";
import { useNavigate, useLocation } from "react-router-dom";

const cx = classNames.bind(styles);

const ItemProduct = (props) => {
  const [imageData, setImageData] = useState();

  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const name = query.get("name");
  const id = query.get("id");

  useEffect(() => {
    // console.log(props.data.id);
    // console.log(props.data);
    getImage();
  }, [props?.data?.id]);

  async function getImage() {
    try {
      const response = await s3
        .getObject({
          Bucket: "gearvm",
          Key: props.data.imageUri || props.data.productImageUri,
        })
        .promise();
      const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
        "base64"
      )}`;

      setImageData(imageSrc);
    } catch (error) {
      console.log(error);
    }
  }
  const navigate = useNavigate();
  const handelItemProduct = () => {
    navigate(
      `/productdetail?name=${props.data.name || props.data.productName}&id=${
        props.data.id || props.data.productId
      }`,
      {
        replace: true,
      }
    );
    window.scrollTo(0, 0);
  };

  return (
    <div className={cx("wrapItemProduct")} onClick={handelItemProduct}>
      <div className={cx("imgProduct")}>
        {/* <Image src={imageData ? imageData : ""} /> */}
        <Customfigure imgUri={imageData} />
      </div>
      <div className={cx("textProduct")}>
        <h5 className={cx("txtNameProduct")}>
          {props.data.name || props.data.productName}
        </h5>
        {/* <div className={cx("content_dis_price")}>
          <h5 className={cx("txtPrice")}>
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(props.data.price)}
          </h5>
          {props.data?.percentageDiscount === 0 ? null : (
            <div className={cx("contentDiscount")}>
              <h5 className={cx("txtDiscount")}>
                {props.data.percentageDiscount}%
              </h5>
            </div>
          )}
        </div> */}
        <h5 className={cx("txt_pricereal")}>
          {" "}
          {new Intl.NumberFormat("de-DE", {
            style: "currency",
            currency: "VND",
          }).format(props.data.price || props.data.productPrice)}
        </h5>
      </div>
    </div>
  );
};

export default ItemProduct;
