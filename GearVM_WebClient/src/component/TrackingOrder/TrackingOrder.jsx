import React, { useState, useEffect } from "react";
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
import { useDispatch, useSelector } from "react-redux";
import { TrackingOrderSlice } from "../../redux/slices/OrderSlices";
import axios from "axios";
import { url } from "../../API/api";

const cx = classNames.bind(styles);
const TrackingOrder = ({ data }) => {
  const dispatch = useDispatch();
  const [order, setOrder] = useState();
  const [activeItem, setActiveItem] = useState();

  // console.log(order);
  // // console.log(data.id);
  const handleShowOrder = async () => {
    setActiveItem(data?.id);
    try {
      const TrackingOrder = await axios.get(`${url}/orders/${data?.id}`, {
        // headers: {
        //   Authorization: `Bearer ${localStorage.getItem("token")}`,
        //   "Content-Type": "application/json",
        // },
      });
      setOrder(TrackingOrder.data);
      return TrackingOrder.data;
    } catch (error) {
      // console.log(error.response.data);
      // console.log(rejectWithValue(error));
      return error.response.data;
    }
  };
  // console.log(order);
  const [status, setStatus] = useState();
  const [active, setActive] = useState();
  function ChangeStatus() {
    switch (data?.orderStatus) {
      case "PAYMENT_PENDING":
        setStatus("Đang chờ thanh toán");

        setActive(1);
        break;
      case "PAYMENT_DONE":
        setStatus("Đang chờ xác nhận");
        setActive(2);
        break;
      case "SHIPPING":
        setStatus("Đang giao hàng");
        setActive(3);
        break;
      case "SHIP_SUCCESS":
        setStatus("Giao hàng thành công");
        setActive(4);
        break;
      case "SHIP_FAIL":
        setStatus("Giao hàng thất bại");
        setActive(5);
        break;
      case "REJECTED":
        setStatus("Đơn hàng bị từ chối");
        setActive(6);
        break;

      default:
    }
  }
  useEffect(() => {
    ChangeStatus();
  }, [data.orderStatus]);

  const options = {
    year: "numeric",
    month: "numeric",
    day: "numeric",
  };
  // console.log(data);

  const [showItem, setShowItem] = useState(true);
  const handleShowItemOrder = () => {
    setShowItem(!showItem);
    console.log(data?.id);
  };
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
          <h6 className={cx("card-txt")}>Order ID: {data?.id}</h6>
          <article className={cx("card")}>
            <Row className={cx("card-body")}>
              <Col>
                <strong>Ngày Tạo Đơn Hàng</strong> <br />
                {new Date(data?.createdDate).toLocaleString("en-US", options)}
              </Col>
              {/* <Col>
                <strong>Nhân Viên Phụ Trách:</strong> <br /> Trần Hoàng Long, |{" "}
                <FontAwesomeIcon icon={faPhone} /> 0394758354{" "}
              </Col> */}
              <Col>
                <strong>Trạng Thái Đơn Hàng:</strong> <br /> {status}
              </Col>
              <Col>
                <strong>Tổng Tiền :</strong> <br />{" "}
                {new Intl.NumberFormat("de-DE", {
                  style: "currency",
                  currency: "VND",
                }).format(data?.totalPrice)}{" "}
              </Col>
            </Row>
          </article>
          {data?.orderStatus === "REJECTED" ? (
            <div>
              <div className={cx("txtReject")}>
                Đơn hàng của bạn đã bị từ chối
              </div>
              <Container>
                <h6
                  className={`${styles.itemShow} ${
                    active === data?.id ? styles.selected : ""
                  }`}
                  onClick={handleShowOrder}
                >
                  Xem chi tiết
                </h6>
                {activeItem === data?.id ? (
                  <Row
                    lg={4}
                    className={cx(
                      `ShowitemOrder${showItem ? "show" : "hidden"}`
                    )}
                  >
                    {order?.orderItems?.map((item, index) => (
                      // <div
                      //   // onClick={() => {
                      //   //   handelItemProduct(item);
                      //   // }}
                      //   className={cx("listProduct_thumb_item")}
                      //   key={item.id}
                      // >
                      <Col key={item.id}>
                        <ItemOrder data={item} />
                      </Col>
                      // </div>
                    ))}
                    {/* </ul> */}
                  </Row>
                ) : null}
              </Container>

              {order?.orderItems?.length > 5 ? (
                <h6
                  className={cx("ShowItemOrder")}
                  onClick={handleShowItemOrder}
                >
                  Xem Thêm
                </h6>
              ) : null}
            </div>
          ) : (
            <div>
              <div className={cx("track")}>
                <div className={cx(`step`, `${active >= 1 ? "active" : null}`)}>
                  {" "}
                  <span className={cx("icon")}>
                    {" "}
                    <FontAwesomeIcon icon={faCheck} />
                    {/* <i className={cx("fa fa-check")}></i> */}
                  </span>{" "}
                  <span className={cx("text")}>Tạo Đơn Hàng Thành Công</span>
                </div>
                <div className={cx(`step`, `${active >= 2 ? "active" : null}`)}>
                  <span className={cx("icon")}>
                    <FontAwesomeIcon icon={faUser} />
                  </span>
                  <span className={cx("text")}> Thanh Toán Thành Công</span>{" "}
                </div>
                <div className={cx(`step`, `${active >= 3 ? "active" : null}`)}>
                  <span className={cx("icon")}>
                    {" "}
                    <FontAwesomeIcon icon={faTruck} />
                  </span>{" "}
                  <span className={cx("text")}>Tiến Hành Giao Hàng</span>{" "}
                </div>
                {active === 5 ? (
                  <div
                    className={cx(`step`, `${active >= 5 ? "active" : null}`)}
                  >
                    {" "}
                    <span className={cx("icon")}>
                      {" "}
                      <FontAwesomeIcon icon={faBox} />
                    </span>{" "}
                    <span className={cx("text")}>Giao Hàng Thất Bại</span>{" "}
                  </div>
                ) : (
                  <div
                    className={cx(`step`, `${active >= 4 ? "active" : null}`)}
                  >
                    {" "}
                    <span className={cx("icon")}>
                      {" "}
                      <FontAwesomeIcon icon={faBox} />
                    </span>{" "}
                    <span className={cx("text")}>Giao Hàng Thành Công</span>{" "}
                  </div>
                )}
              </div>
              <hr />

              {/* <Slider {...settings}>
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
            </Slider> */}
              <Container>
                <h6
                  className={`${styles.itemShow} ${
                    active === data?.id ? styles.selected : ""
                  }`}
                  onClick={handleShowOrder}
                >
                  Xem chi tiết
                </h6>
                {activeItem === data?.id ? (
                  <Row
                    lg={4}
                    className={cx(
                      `ShowitemOrder${showItem ? "show" : "hidden"}`
                    )}
                  >
                    {order?.orderItems?.map((item, index) => (
                      // <div
                      //   // onClick={() => {
                      //   //   handelItemProduct(item);
                      //   // }}
                      //   className={cx("listProduct_thumb_item")}
                      //   key={item.id}
                      // >
                      <Col key={item.id}>
                        <ItemOrder data={item} />
                      </Col>
                      // </div>
                    ))}
                    {/* </ul> */}
                  </Row>
                ) : null}
              </Container>

              {order?.orderItems?.length > 5 ? (
                <h6
                  className={cx("ShowItemOrder")}
                  onClick={handleShowItemOrder}
                >
                  Xem Thêm
                </h6>
              ) : null}
            </div>
          )}

          {/* <hr /> */}
        </div>
      </article>
    </Container>
  );
};

export default TrackingOrder;
