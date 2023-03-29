import React from "react";
import styles from "./TrackingOrder.module.scss";
import classNames from "classnames/bind";
import { Container } from "react-bootstrap";
import { Col, Row } from "react-bootstrap";
import ItemOrder from "../itemOrder/ItemOrder";
import dataTrackingOrder from "../../dataUI/dataTrackingOrder";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import {
  faBox,
  faCheck,
  faPhone,
  faTruck,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const cx = classNames.bind(styles);
const TrackingOrder = () => {
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
  return (
    <Container>
      <article className={cx("card")}>
        <hr />
        {/* <header className={cx("card-header")}> My Orders / Tracking </header> */}
        <div className={cx("card-pad")}>
          <h6 className={cx("card-txt")}>Order ID: OD45345345435</h6>
          <article className={cx("card")}>
            <Row className={cx("card-body")}>
              <Col>
                <strong>Ngày Tạo Đơn Hàng</strong> <br />
                29-3-2023
              </Col>
              <Col>
                <strong>Nhân Viên Phụ Trách:</strong> <br /> Trần Hoàng Long, |{" "}
                <FontAwesomeIcon icon={faPhone} /> 0394758354{" "}
              </Col>
              <Col>
                <strong>Tráng Thái Đơn Hàng:</strong> <br /> Nhân Viên Đã Xác
                Nhận{" "}
              </Col>
              <Col>
                <strong>Tổng Tiền :</strong> <br />{" "}
                {new Intl.NumberFormat("de-DE", {
                  style: "currency",
                  currency: "VND",
                }).format(1000000)}{" "}
              </Col>
            </Row>
          </article>
          <div className={cx("track")}>
            <div className={cx(`step`, `active`)}>
              {" "}
              <span className={cx("icon")}>
                {" "}
                <FontAwesomeIcon icon={faCheck} />
                {/* <i className={cx("fa fa-check")}></i> */}
              </span>{" "}
              <span className={cx("text")}>Tạo Đơn Hàng Thành Công</span>
            </div>
            <div className={cx(`step`, `active`)}>
              <span className={cx("icon")}>
                <FontAwesomeIcon icon={faUser} />
              </span>
              <span className={cx("text")}> Nhân Viên Đã Xác Nhận</span>{" "}
            </div>
            <div className={cx("step")}>
              <span className={cx("icon")}>
                {" "}
                <FontAwesomeIcon icon={faTruck} />
              </span>{" "}
              <span className={cx("text")}>Tiến Hành Giao Hàng</span>{" "}
            </div>
            <div className={cx("step")}>
              {" "}
              <span className={cx("icon")}>
                {" "}
                <FontAwesomeIcon icon={faBox} />
              </span>{" "}
              <span className={cx("text")}>Giao Hàng Thành Công</span>{" "}
            </div>
          </div>
          <hr />
          <ul className={cx("row")}>
            {/* <li className={cx("col-md-4")}> */}
            <Slider {...settings}>
              {dataTrackingOrder?.map((item, index) => (
                <div
                  // onClick={() => {
                  //   handelItemProduct(item);
                  // }}
                  className={cx("listProduct_thumb_item")}
                  key={item.id}
                >
                  <ItemOrder data={item} />
                </div>
              ))}
            </Slider>
            {/* </li> */}
          </ul>
          {/* <hr /> */}
        </div>
      </article>
    </Container>
  );
};

export default TrackingOrder;
