import React from "react";
import styles from "./Order.module.scss";
import classNames from "classnames/bind";
import { Button, Checkbox } from "antd";
import Table from "react-bootstrap/Table";
import { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import TrackingOrder from "../TrackingOrder/TrackingOrder";
import { Container } from "react-bootstrap";
import CustomButon from "../Custom/CustomButon/CustomButon";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faChevronLeft,
  faChevronRight,
} from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import orderSlices, {
  GetAllOrderSlice,
  TrackingOrderSlice,
} from "../../redux/slices/OrderSlices";
const cx = classNames.bind(styles);
const Order = () => {
  const dispatch = useDispatch();

  const Allorder = useSelector((state) => state.order.AllOrder);

  console.log(Allorder);
  // const [order, setOder] = useState();
  const options = {
    year: "numeric",
    month: "numeric ",
    day: "numeric",
  };

  useEffect(() => {
    dispatch(GetAllOrderSlice());
  }, []);

  return (
    <Container>
      <div className={cx("wrapOrder")}>
        <div className={cx("wrapOrder_thumb")}>
          <h5>Lịch Sử Đơn Hàng</h5>
          <Link to="/" className={cx("btn", "btn-warning")}>
            Tiếp tục Mua Hàng
            <FontAwesomeIcon
              icon={faChevronRight}
              style={{ marginTop: 5, marginLeft: 5 }}
            />
          </Link>
        </div>
        {Allorder?.map((item) => (
          <TrackingOrder data={item} key={item.id} />
        ))}
      </div>
    </Container>
  );
};

export default Order;
