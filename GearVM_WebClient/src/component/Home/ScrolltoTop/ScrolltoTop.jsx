import React from "react";
import styles from "./ScrolltoTop.modulo.scss";
import classNames from "classnames/bind";
import { UpOutlined } from "@ant-design/icons";
import { useContext } from "react";
import { AppContext } from "../../context/AppProvider";

const cx = classNames.bind(styles);
const ScrolltoTop = () => {
  const { setShowChat } = useContext(AppContext);

  const handleShowChatBox = () => {
    setShowChat(true);
  };

  document.addEventListener("scroll", () => {
    const add = document.documentElement.scrollTop;
    const top = document.documentElement.clientHeight;
    if (add >= top) {
      document.querySelector(".btnTop")?.classList.add("show");

      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".btnTop")?.classList.remove("show");
    }
  });
  const top = () => {
    window.scrollTo(0, 0);
  };
  const message = () => {
    window.location.assign(
      "https://www.facebook.com/messages/t/117090754650837"
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
        {/* <img src={require("../../../assets/scroll.jpg")} /> */}
        <div className={cx("ChatEmployeeImg")}></div>
      </div>
      <div>
        <img
          src={require("../../../assets/chatbox.png")}
          className={cx("chatBox")}
          onClick={handleShowChatBox}
        />
      </div>
    </div>
  );
};

export default ScrolltoTop;
