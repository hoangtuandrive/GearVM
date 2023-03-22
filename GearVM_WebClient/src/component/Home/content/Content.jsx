import React from "react";
import styles from "./Content.modulo.scss";
import classNames from "classnames/bind";
import { Image } from "antd";
import { Container, Row, Col } from "react-bootstrap";

const cx = classNames.bind(styles);
const Content = () => {
  document.addEventListener("scroll", () => {
    const top = document.documentElement.scrollTop;

    if (top >= 800) {
      document.querySelector(".Product_Img_scroll").classList.add("animate");

      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".Product_Img_scroll").classList.remove("animate");
      document.querySelector(".GoodAbout").classList.remove("animate");
    }
    if (top >= 1200) {
      document.querySelector(".GoodAbout").classList.add("animate");
      document.querySelector(".GoodAbout_bot").classList.add("animate");
    } else {
      document.querySelector(".GoodAbout").classList.remove("animate");
      document.querySelector(".GoodAbout_bot").classList.add("animate");
    }
    if (top >= 2180) {
      document.querySelector(".Content_mid").classList.add("animate");
    } else {
      document.querySelector(".Content_mid").classList.remove("animate");
    }
  });
  return (
    <div>
      <div className={cx("Product_Img")}>
        <img
          src={require("../../../assets/laptop1.jpg")}
          className={cx("img")}
        />
        <div className={cx("product-home__image")}>
          <img
            src={require("../../../assets/laptop3.jpg")}
            className={cx("img")}
          />

          <img
            src={require("../../../assets/laptop4.jpg")}
            className={cx("img")}
          />
        </div>
      </div>
      <div className={cx("Product_Img_scroll")}>
        <img
          src={require("../../../assets/tainghe3.jpg")}
          className={cx("img")}
        />
        <div className={cx("product-home__image")}>
          <img
            src={require("../../../assets/tainghe4.jpg")}
            className={cx("img")}
          />

          <img
            src={require("../../../assets/tainghe5.jpg")}
            className={cx("img")}
          />
        </div>
      </div>
      <div className={cx("GoodAbout")}>
        <div className={cx("leftGood")}>
          <h4 className={cx("txtGood_header")}>Laptop</h4>
          <h2 className={cx("txtGood")}>
            THĂNG HẠNG PHONG CÁCH - NÂNG TẦM PHONG THÁI
          </h2>
        </div>
        <div>
          <img
            src={require("../../../assets/banphim1.jpg")}
            className={cx("rightGood")}
          />
        </div>
      </div>
      <div className={cx("GoodAbout_bot")}>
        <div className={cx("leftGood")}>
          <img
            src={require("../../../assets/chuot.jpg")}
            className={cx("rightGood_a")}
          />
        </div>
        <div className={cx("leftGood")}>
          <h2 className={cx("txtGood_a")}>SỰ KẾT HỢP MỚI</h2>
        </div>
      </div>
      <Container className={cx("Content_mid")}>
        <Row>
          <Col lg={6}>
            <img
              src={require("../../../assets/tainghe.jpg")}
              className={cx("Content_mid_img")}
            ></img>
          </Col>
          <Col lg={6}>
            <img
              src={require("../../../assets/tainghe2.jpg")}
              className={cx("Content_mid_img")}
            ></img>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Content;
