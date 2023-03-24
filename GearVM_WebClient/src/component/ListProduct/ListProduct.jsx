import React from "react";

import classNames from "classnames/bind";
import styles from "./ListProduct.modulo.scss";
import { Image } from "antd";
import ItemProduct from "../itemProduct/ItemProduct";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { useLocation, useNavigate } from "react-router-dom";

const cx = classNames.bind(styles);
const ListProduct = (props) => {
  // console.log(props)
  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 2,
    initialSlide: 0,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
          infinite: true,
          dots: true,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          initialSlide: 2,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        },
      },
    ],
  };
  const navigate = useNavigate();
  const location = useLocation();

  const handelItemProduct = (item) => {
    navigate(`/productdetail?name=${item.name}&id=${item.id}`, {
      replace: true,
    });
  };
  // console.log(props.data);
  return (
    <Slider {...settings}>
      {props.data?.map((item, index) => (
        <div
          onClick={() => {
            handelItemProduct(item);
          }}
          className={cx("listProduct_thumb_item")}
          key={item.id}
        >
          <ItemProduct data={item} />
        </div>
      ))}
    </Slider>
  );
};
export default ListProduct;
