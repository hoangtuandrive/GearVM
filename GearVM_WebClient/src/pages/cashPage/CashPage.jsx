import React from "react";
import styles from "./CashPage.module.scss";
import classNames from "classnames/bind";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
const cx = classNames.bind(styles);
const CashPage = () => {
  const navigate = useNavigate();
  const handleComplete = () => {
    navigate("/");
  };
  return (
    <div className={cx("wrapper")}>
      <form action="#" className={cx("card-content")}>
        <div className={cx("container")}>
          <div className={cx("image")}>
            <FontAwesomeIcon icon={faCheck} className={cx("iconCheck")} />
            {/* <i className="fas fa-envelope"></i> */}
          </div>
          <h1>Thành Công</h1>
          <p>
            Cảm ơn quý khách đã đặt đơn hàng, vui lòng đợi nhân viên chúng tôi
            xác nhận và gọi điện.
          </p>
        </div>

        <button className={cx("subscribe-btn")} onClick={handleComplete}>
          Tiếp tục mua hàng
        </button>
      </form>
    </div>
  );
};

export default CashPage;
