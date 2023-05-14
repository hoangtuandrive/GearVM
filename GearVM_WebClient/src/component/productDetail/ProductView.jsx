import React, { useContext } from "react";
import styles from "./ProductView.module.scss";
import classNames from "classnames/bind";
import CustomDiscount from "../customDiscount/CustomDiscount";
import { useNavigate, useLocation, useNavigation } from "react-router-dom";
import { useDispatch } from "react-redux";
import CartSlice from "../../redux/slices/CartSlices";
import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import { Image } from "antd";
import { Col, Container } from "react-bootstrap";
import { s3 } from "../../aws";
import CustomButon from "../Custom/CustomButon/CustomButon";
import ChatBox from "../chatBox/ChatBox";
import { AppContext } from "../context/AppProvider";
import ListProductHome from "../Home/listProductofHome/ListProductHome";
const cx = classNames.bind(styles);

const ProductView = () => {
  const [productDetail, setProductDetail] = useState([]);
  const navigate = useNavigate();
  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const productId = query.get("id");
  const ProductName = query.get("name");

  const { showChat } = useContext(AppContext);
  const productUrl = `http://localhost:8080/api/products/${productId}`;

  const [imageData, setImageData] = useState(null);

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
    // console.log(213);
  }, [productUrl]);

  // console.log(productDetail);
  useEffect(() => {
    const getImage = async () => {
      try {
        const response = await s3
          .getObject({
            Bucket: "gearvm",
            Key: productDetail.imageUri,
          })
          .promise();
        const imageSrc = `data:image/jpeg;base64,${response.Body.toString(
          "base64"
        )}`;
        // console.log(imageSrc);
        setImageData(imageSrc);
      } catch (error) {
        // console.log(error);
      }
    };
    getImage();
  }, [productDetail]);

  const dispatch = useDispatch();
  const handleAddToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
  };
  const handleBuyToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product));
    navigate("/cart", { replace: true });
  };
  let text = productDetail.description;

  // Thay thế ký tự gạch đầu dòng bằng ký tự xuống dòng
  // let formattedText = text?.replace(/-/g).trim();

  // Tách chuỗi thành mảng các dòng
  let lines = text?.split("\n");

  const [product, setProduct] = useState([]);

  useEffect(() => {
    if (productDetail.type !== undefined) {
      console.log(productId);
      console.log(productDetail.type);
      const productUrlRelative = `http://localhost:8080/api/products/Relative?type=${productDetail.type}&id=${productId}`;
      const fetchProduct = async () => {
        try {
          const rep = await axios.get(productUrlRelative);
          setProduct(rep.data);
        } catch (error) {
          console.log(error);
        }
      };
      fetchProduct();
    }
  }, [productDetail?.type, productId]);

  // console.log(product);
  return (
    <Container>
      <div className={cx("wrapProductView")}>
        {/* {console.log(productDetail)} */}

        <div className={cx("ProductView_Img")}>
          <Image
            src={imageData ? imageData : ""}
            className={cx("ProductView_Img")}
          />
        </div>

        <div className={cx("ProductView_Content")}>
          <h1 className={cx("ProductView_txtName")}>{productDetail.name}</h1>
          <div className={cx("ProductView_Content_origin")}>
            <h4 className={cx("ProductView_txtTrademark")}>
              Thương hiệu
              <span className={cx("ProductView_txtTrademark_Name")}>
                {" "}
                {productDetail.brand}
              </span>
            </h4>
            <h4 className={cx("ProductView_txtTrademark")}>
              Mã sản phẩm:
              <span className={cx("ProductView_txtTrademark_Name")}>
                {" "}
                {productDetail.id}
              </span>
            </h4>
          </div>
          <h5 className={cx("ProductView_txtPrice")}>
            {" "}
            {new Intl.NumberFormat("de-DE", {
              style: "currency",
              currency: "VND",
            }).format(productDetail.price)}
          </h5>
          {/* <div className={cx("ProductView_Discount")}>
            <h5 className={cx("ProductView_txtPrice_dis")}>
              {new Intl.NumberFormat("de-DE", {
                style: "currency",
                currency: "VND",
              }).format(productDetail.price)}
            </h5>
            {productDetail?.percentageDiscount === 0 ? null : (
              <div className={cx("contentDiscount")}>
                <h5 className={cx("txtDiscount")}>
                  {productDetail.percentageDiscount}%
                </h5>
              </div>
            )}
          </div> */}
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
              value="Thêm Vào Giỏ Hàng"
              className={cx("ProductView_btn_Addcard")}
              onClick={() => handleAddToCart(productDetail)}
            />
          </div>
        </div>
      </div>
      <div className={cx("wrap_description")}>
        <div className={cx("ProductView_description")}>
          {lines?.map((item, index) => (
            <p key={index}>{item}</p>
          ))}
        </div>
      </div>
      <ListProductHome name="Sản Phẩm Liên Quan" data={product} />
      {/* {console.log(product)} */}
      {showChat ? <ChatBox name={productDetail.name} /> : null}
    </Container>
  );
};

export default ProductView;
