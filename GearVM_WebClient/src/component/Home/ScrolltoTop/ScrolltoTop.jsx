import React from "react";
import styles from "./ScrolltoTop.modulo.scss";
import classNames from "classnames/bind";
import { UpOutlined } from "@ant-design/icons";
const cx = classNames.bind(styles);
const ScrolltoTop = () => {
  const top = () => {
    window.scrollTo(0, 0);
  };
  const message = () => {
    window.location.assign(
      "https://business.facebook.com/latest/inbox/messenger?asset_id=117090754650837&bpn_id=6031765276914164&nav_ref=redirect_biz_inbox&mailbox_id=&selected_item_id=100009761532540"
    );
  };
  return (
    <div className={cx("Scroll")}>
      <div className={cx("btnTop")} onClick={top}>
        <UpOutlined
          style={{
            color: "white",
          }}
        />
      </div>
      <div onClick={message}>
        <img src={require("../../../assets/scroll.jpg")} />
      </div>
    </div>
  );
};

export default ScrolltoTop;
