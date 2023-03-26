import React from "react";
import styles from "./Order.module.scss";
import classNames from "classnames/bind";
import { Button, Checkbox } from "antd";
import Table from "react-bootstrap/Table";
import { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
const cx = classNames.bind(styles);
const Order = () => {
  const [order, setOder] = useState();
  const options = {
    year: "numeric",
    month: "numeric",
    day: "numeric",
  };
  useEffect(() => {
    const fetchOrder = async () => {
      const productUrl = `http://localhost:8080/api/orders`;
      try {
        const rep = await axios.get(productUrl);
        setOder(rep.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchOrder();
  }, []);

  console.log(order);
  return (
    <div className={cx("wrapOrder")}>
      <div className={cx("wrapOrder_thumb")}>
        <h5>Lịch Sử Đơn Hàng</h5>
        {/* <div>
                    <Button type="primary" >Chờ Thanh Toán</Button>
                    <Button>Chờ Giao Hàng</Button>
                    <Button>Đã Hoàn Thành</Button>
                </div> */}
      </div>
      {/* <div className={cx('wrapOrder_content')}> */}
      <div className={cx("wrapListCart_Content")}>
        <Table>
          <thead>
            <tr>
              <th>
                <h5 className={cx("wrapListCart_Content_tr_text")}>
                  Mã hóa đơn
                </h5>
              </th>
              <th>Ngày thanh toán</th>
              <th>Trạng thái</th>
              <th>Tổng tiền</th>
            </tr>
          </thead>
          <tbody>
            {order?.map((item) => (
              <tr key={item.id}>
                <td>
                  <div className={cx("wrapListCart_Content_NameProduct")}>
                    {/* <img
                    src="https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw"
                    className={cx("wrapListCart_Content_img")}
                  /> */}
                    <div>
                      <p className={cx("wrapListCart_Content_Name")}>
                        {item.id}
                      </p>
                    </div>
                  </div>
                </td>
                <td>
                  <h5>
                    {/* {item.createdDate} */}
                    {new Date(item.createdDate).toLocaleDateString(
                      "en-US",
                      options
                    )}
                    {/* toDateString("vi-VN"),options} */}
                  </h5>
                </td>
                <td>
                  {/* <p className={cx("wrapListCart_Content_quantiy")}> */}
                  {item.orderStatus}
                  {/* </p> */}
                </td>
                <td>
                  <h5>
                    {new Intl.NumberFormat("de-DE", {
                      style: "currency",
                      currency: "VND",
                    }).format(item.totalPrice)}
                  </h5>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>

    // </div>
  );
};

export default Order;
