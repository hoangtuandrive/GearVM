import React, { useContext } from "react";
import styles from "./PayBody.module.scss";
import classNames from "classnames/bind";
import ItemCard from "../itemCard/ItemCard";
import ModalAddress from "../ModalAddress/ModalAddress";
import { AppContext } from "../context/AppProvider";
import { Input } from "antd";
const cx = classNames.bind(styles);
const PayBody = () => {
  const { setShow } = useContext(AppContext);

  const handleShow = () => {
    setShow(true);
  };
  return (
    <div className={cx("wrapPayBody")}>
      <div className={cx("wrapPayBody_right")}>
        <div className={cx("wrapPayBody_right_address")}>
          <h5>Thông tin nhận hàng</h5>
          <div
            className={cx("wrapPayBody_right_address_btn")}
            onClick={handleShow}
          >
            <h3>+</h3>
            <h5>Thêm Địa chỉ</h5>
          </div>
        </div>
        <div className={cx("wrapPayBody_right_address")}>
          <h5>Mã giảm giá</h5>
          <Input placeholder="nhập mã giảm giá" />
        </div>
        <div className={cx("wrapPayBody_right_metodpay")}>
          <h5>Phương thức thanh toán</h5>
          <div className={cx("wrapPayBody_right_metodpay_wrapbtn")}>
            <div className={cx("wrapPayBody_right_address_btn")}>
              <h5 style={{ color: "black" }}>Thanh toán VNPAY-QR</h5>
              <h6>
                Thanh toán qua Internet Banking, Visa, Master, JCB, VNPAY-QR
              </h6>
            </div>
            <div className={cx("wrapPayBody_right_address_btn")}>
              <h5 style={{ color: "black" }}>Thanh toán khi nhận hàng</h5>
            </div>
          </div>
        </div>
      </div>
      <div className={cx("wrapPayBody_left")}>
        <div className={cx("wrapPayBody_right_infoProduct")}>
          <h5>Thông tin đơn hàng</h5>

          <div className={cx("wrapPayBody_right_infoProduct_wrap")}>
            {/* <ItemCard />        
                    <ItemCard />           
                    <ItemCard />            */}
          </div>
        </div>
        <div className={cx("wrapPayBody_right_Sumpay")}>
          <h5>Thanh toán</h5>
          <div>
            <div className={cx("wrapPayBody_right_Sumpay_content")}>
              <span className={cx("wrapPayBody_right_Sumpay_text")}>
                Tổng tạm tính:
              </span>
              <span className={cx("wrapPayBody_right_Sumpay_text")}>
                75.768.000₫
              </span>
            </div>
            <div className={cx("wrapPayBody_right_Sumpay_content")}>
              <span className={cx("wrapPayBody_right_Sumpay_text")}>
                Phí vận chuyển
              </span>
              <span className={cx("wrapPayBody_right_Sumpay_text")}>
                Miễn phí
              </span>
            </div>
            <div className={cx("wrapPayBody_right_Sumpay_content")}>
              <span className={cx("wrapPayBody_right_Sumpay_text")}>
                Thành Tiền:
              </span>
              <span className={cx("wrapPayBody_right_Sumpay_text_red")}>
                75.768.000₫
              </span>
            </div>
          </div>
          <input
            type="button"
            value="Thanh Toán"
            className={cx("wrapPayBody_right_Sumpay_btn")}
          />
        </div>
      </div>

      <ModalAddress />
    </div>
  );
};

export default PayBody;
