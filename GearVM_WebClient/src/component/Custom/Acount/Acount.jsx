import React, { useEffect } from "react";
import styles from "./Acount.module.scss";
import classNames from "classnames/bind";
import { useDispatch, useSelector } from "react-redux";
import { currentCustomer } from "../../../redux/slices/AuthSlices";

const cx = classNames.bind(styles);
const Acount = () => {
  const token = localStorage.getItem("token");
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(currentCustomer());
  }, []);

  const currentUser = useSelector((state) => state.auth);

  return (
    <div className={cx("wrapAcount")}>
      <img
        src="https://i.pinimg.com/originals/dd/0e/86/dd0e86cc6c9ae4bc0473c762e275f9c4.jpg"
        className={cx("wrapAcount_img")}
      />
      <div className={cx("wrapAcount_thumb")}>
        <p className={cx("wrapAcount_thumb_txt")}>Xin chào,</p>
        <p className={cx("wrapAcount_thumb_txt")}>{currentUser.user.name}</p>
      </div>
    </div>
  );
};

export default Acount;
