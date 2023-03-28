import React from "react";
import styles from "./home.modulo.scss";
import classNames from "classnames/bind";
import Header from "../../component/Home/header/Header";

import SlideShow from "../../component/Home/slideShow/SlideShow";
import NavModal from "../../component/Modal/NavModal";
import ListProductHome from "../../component/Home/listProductofHome/ListProductHome";
import Footer from "../../component/Home/footer/Footer";
import Content from "../../component/Home/content/Content";
import ScrolltoTop from "../../component/Home/ScrolltoTop/ScrolltoTop";
const cx = classNames.bind(styles);
const Home = () => {
  return (
    <div className={cx("wrapHome")}>
      <Header></Header>
      {/* <NavModal/> */}
      <SlideShow />
      <Content></Content>
      <ListProductHome name="SẢN PHẨM BÁN CHẠY"/>
      <ScrolltoTop />
      {/* <ListProduct /> */}
      <Footer />
    </div>
  );
};

export default Home;
