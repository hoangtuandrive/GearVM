import React from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import ProductView from "../component/productDetail/ProductView";
import styles from "../sass/Productdetail.module.scss";
import classNames from "classnames/bind";
import Review from "../component/review/Review";
import itemReviewComment from "../component/itemReview/itemReview";
const cx = classNames.bind(styles);
const PageProductDetail = () => {
  return (
    <div className={cx("wrapProductDetail")}>
      <Header />
      <ProductView />
      {/* <Review></Review> */}
      <Footer />
    </div>
  );
};

export default PageProductDetail;
