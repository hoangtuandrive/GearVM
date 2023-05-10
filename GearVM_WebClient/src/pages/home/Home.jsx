import React, { useEffect, useState } from "react";
import styles from "./home.modulo.scss";
import classNames from "classnames/bind";
import Header from "../../component/Home/header/Header";

import SlideShow from "../../component/Home/slideShow/SlideShow";
import NavModal from "../../component/Modal/NavModal";
import ListProductHome from "../../component/Home/listProductofHome/ListProductHome";
import Footer from "../../component/Home/footer/Footer";
import Content from "../../component/Home/content/Content";
import ScrolltoTop from "../../component/Home/ScrolltoTop/ScrolltoTop";
import ChatBox from "../../component/chatBox/ChatBox";
import { useContext } from "react";
import { AppContext } from "../../component/context/AppProvider";
import axios from "axios";

const cx = classNames.bind(styles);
const Home = () => {
  const { showChat } = useContext(AppContext);
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
  console.log(product.productList);
  return (
    <div className={cx("wrapHome")}>
      <Header></Header>
      {/* <NavModal/> */}
      <SlideShow />
      <Content></Content>
      <ListProductHome name="SẢN PHẨM BÁN CHẠY" data={product} />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      {/* <ListProduct /> */}
      <Footer />
    </div>
  );
};

export default Home;
