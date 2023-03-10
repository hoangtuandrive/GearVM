import React from "react";
import styles from "./ProductView.module.scss";
import classNames from "classnames/bind";
import CustomDiscount from "../customDiscount/CustomDiscount";
import { useNavigate, useLocation, useNavigation } from "react-router-dom";
import { useDispatch } from "react-redux";
import CartSlice from "../../redux/slices/CartSlices";
import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import { Col, Container } from "react-bootstrap";

const cx = classNames.bind(styles);

const ProductView = () => {
  const [productDetail, setProductDetail] = useState([]);
  const navigate = useNavigate();
  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const productId = query.get("id");

  const productUrl = `http://localhost:8080/api/products/${productId}`;

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const rep = await axios.get(productUrl);
        setProductDetail(rep.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProduct();
  }, []);

  const dispatch = useDispatch();
  const handleAddToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
  };
  const handleBuyToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
    navigate("/cart", { replace: true });
  };

  return (
    <Container>
      <div className={cx("wrapProductView")}>
        {console.log(productDetail)}

        <div className={cx("ProductView_Img")}>
          <img
            src="https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw"
            className={cx("ProductView_Img_main")}
          />
          {/* <div className={cx("ProductView_Img_list")}>
          <img
            src="https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw"
            className={cx("ProductView_Img_itemImg")}
          />
          <img
            src="https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw"
            className={cx("ProductView_Img_itemImg")}
          />
          <img
            src="https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw"
            className={cx("ProductView_Img_itemImg")}
          />
          <img
            src="https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw"
            className={cx("ProductView_Img_itemImg")}
          />
        </div> */}
        </div>

        <div className={cx("ProductView_Content")}>
          <h1 className={cx("ProductView_txtName")}>{productDetail.name}</h1>
          <div className={cx("ProductView_Content_origin")}>
            <h4 className={cx("ProductView_txtTrademark")}>
              Th????ng hi???u
              <span className={cx("ProductView_txtTrademark_Name")}> Asus</span>
            </h4>
            <h4 className={cx("ProductView_txtTrademark")}>
              M?? s???n ph???m:
              <span> 141</span>
            </h4>
          </div>
          <h5 className={cx("ProductView_txtPrice")}>{productDetail.price}???</h5>
          <div className={cx("ProductView_Discount")}>
            <h5 className={cx("ProductView_txtPrice_dis")}>
              {productDetail.price}???
            </h5>
            <div className={cx("contentDiscount")}>
              <h5 className={cx("txtDiscount")}>4%</h5>
            </div>
          </div>
          <CustomDiscount />
          <div className={cx("ProductView_btn")}>
            <input
              type="button"
              value="Mua Ngay"
              className={cx("ProductView_btn_Buy")}
              onClick={() => handleBuyToCart(productDetail)}
            />
            <input
              type="button"
              value="Th??m V??o Gi??? H??ng"
              className={cx("ProductView_btn_Addcard")}
              onClick={() => handleAddToCart(productDetail)}
            />
          </div>
        </div>
      </div>
    </Container>
  );
};

export default ProductView;
