import React, { useState } from "react";
import styles from "./NavModal.modulo.scss";
import classNames from "classnames/bind";
import { LaptopOutlined } from "@ant-design/icons";
import Filter from "../Filter/Filter";
import { useEffect } from "react";
import Tippy from "@tippyjs/react/headless";
import dataNavModal from "../../dataUI/dataNavModal";
const cx = classNames.bind(styles);
// const $=document.querySelector.bind(document)
const NavModal = ({ data }) => {
  const $$ = document.querySelectorAll.bind(document);
  const navContent = $$(".NavContent_item");
  //   console.log(navContent);
  const top = document.documentElement.scrollTop;
  const [openFilter, setopenFilter] = useState(false);
  document.addEventListener("scroll", () => {
    const top = document.documentElement.scrollTop;
    if (top != 0) {
      document.querySelector(".wrapNavModal").classList.add("sticky");
      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".wrapNavModal").classList.remove("sticky");
    }
  });
  useEffect(() => {
    if (data === true) {
      document.querySelector(".wrapNavModal").classList.add("active");
    } else {
      document.querySelector(".wrapNavModal").classList.remove("active");
    }
  }, [!data]);
  return (
    <div className={cx("wrapNavModal ")}>
      <div className={cx("NavContent")}>
        {/* <Tippy
          interactive
          placement="right-start"
          render={(attrs) => (
            <div className="tipBox" tabIndex="-1" {...attrs}>
              <Filter />
            </div>
          )}
        >
          <div className={cx("NavContent_item active")}>
            <LaptopOutlined style={{ fontSize: 20 }} />
            <span style={{ fontSize: 18, marginLeft: 15 }}>Laptop</span>
          </div>
        </Tippy> */}

        {dataNavModal.map((item) => (
          <Tippy
            key={item.id}
            interactive
            placement="right-start"
            render={(attrs) => (
              <div className="tipBox" tabIndex="-1" {...attrs}>
                <Filter data={item} />
              </div>
            )}
          >
            <div className={cx("NavContent_item")} key={item.id}>
              <LaptopOutlined style={{ fontSize: 20 }} />
              <span style={{ fontSize: 18, marginLeft: 15 }}>{item.name}</span>
            </div>
          </Tippy>
        ))}
        <div className={cx("NavContent_item")}>
          <LaptopOutlined style={{ fontSize: 20 }} />
          <span style={{ fontSize: 18, marginLeft: 15 }}>Màn hình</span>
        </div>
        <div className={cx("NavContent_item")}>
          <LaptopOutlined style={{ fontSize: 20 }} />
          <span style={{ fontSize: 18, marginLeft: 15 }}>Bàn phím</span>
        </div>
        <div className={cx("NavContent_item")}>
          <LaptopOutlined style={{ fontSize: 20 }} />
          <span style={{ fontSize: 18, marginLeft: 15 }}>Chuột</span>
        </div>
        <div className={cx("NavContent_item")}>
          <LaptopOutlined style={{ fontSize: 20 }} />
          <span style={{ fontSize: 18, marginLeft: 15 }}>PC doanh nghiệp</span>
        </div>
      </div>
    </div>
  );
};

export default NavModal;
