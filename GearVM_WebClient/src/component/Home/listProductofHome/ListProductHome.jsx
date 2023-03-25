import React from "react";
import classNames from "classnames/bind";
import styles from "./ListProductHome.modulo.scss";
import ListProduct from "../../ListProduct/ListProduct";
import { useEffect, useState } from "react";
import axios from "axios";
const cx = classNames.bind(styles);
const ListProductHome = () => {
  const [product, setProduct] = useState([]);

  const productUrl =
    "http://localhost:8080/api/products?pageNumber=0&pageSize=20&sortBy=id";

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const rep = await axios.get(productUrl);
        setProduct(rep.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProduct();
  }, []);
  // console.log(product.productList);
  return (
    <div className={cx("wrapListHome")}>
      <div className={cx("WrapListProduct_Laptop")}>
        <h1 className={cx("lblwraplistproduct")}>SẢN PHẨM BÁN CHẠY</h1>
        <ListProduct data={product.productList} />
      </div>
    </div>
  );
};

export default ListProductHome;
