import React from "react";
import styles from "./Order.module.scss";
import classNames from "classnames/bind";
import { Button, Checkbox } from "antd";
import Table from "react-bootstrap/Table";
const cx = classNames.bind(styles);
const Order = () => {
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
            <tr>
              <td>
                <div className={cx("wrapListCart_Content_NameProduct")}>
                  {/* <img
                    src="https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw"
                    className={cx("wrapListCart_Content_img")}
                  /> */}
                  <div>
                    <p className={cx("wrapListCart_Content_Name")}>
                      HD03232023
                    </p>
                  </div>
                </div>
              </td>
              <td>
                <h5>03-23-2023</h5>
              </td>
              <td>
                {/* <p className={cx("wrapListCart_Content_quantiy")}> */}
                Đang giao hàng
                {/* </p> */}
              </td>
              <td>
                {" "}
                <h5>30.889.000đ</h5>
              </td>
            </tr>
          </tbody>
        </Table>
      </div>
    </div>

    // </div>
  );
};

export default Order;
