import React, { useContext } from "react";
import styles from "./MenuAcount.module.scss";
import { LaptopOutlined } from "@ant-design/icons";
import classNames from "classnames/bind";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppProvider";
import ModalUser from "../ModalUser/ModalUser";
import { useDispatch } from "react-redux";
import authSlice from "../../redux/slices/AuthSlices";
const cx = classNames.bind(styles);
const MenuAcount = () => {
  const dispatch = useDispatch();
  const { setUserOpen } = useContext(AppContext);
  const navigate = useNavigate();
  const handleOrder = () => {
    navigate("/orderManager", { replace: true });
  };
  const handleOpenUser = () => {
    setUserOpen(true);
  };
  const handleLogout = () => {
    dispatch(authSlice.actions.logoutUser(null));
  };
  return (
    <div className={cx("wrapMenuAcount")}>
      <div className={cx("wrapMenuAcount_thumb")} onClick={handleOpenUser}>
        <LaptopOutlined />
        <p className={cx("wrapMenuAcount_thumb_txt")}>Thông tin tài khoản</p>
      </div>
      <div className={cx("wrapMenuAcount_thumb")} onClick={handleOrder}>
        <LaptopOutlined />
        <p className={cx("wrapMenuAcount_thumb_txt")}>Lịch sử đơn hàng</p>
      </div>
      <div className={cx("wrapMenuAcount_thumb")}>
        <LaptopOutlined />
        <p className={cx("wrapMenuAcount_thumb_txt")}>Số địa chỉ</p>
      </div>
      <input
        type="button"
        className={cx("wrapMenuAcount_thumb_btn")}
        value="Đăng xuất"
        onClick={handleLogout}
      />
      <ModalUser />
    </div>
  );
};

export default MenuAcount;
