import React, { useEffect } from "react";
import styles from "./Header.modulo.scss";
import classNames from "classnames/bind";   
import { Card, Image,Input } from 'antd';
import {UserOutlined,ShoppingCartOutlined } from "@ant-design/icons";

import Nav from '../nav/Nav';
import Tippy from '@tippyjs/react/headless';
import MenuCard from "../../card/Card";

// const {  AudioOutlined  } = icons;
const { Search } = Input;
const cx = classNames.bind(styles);
const Header = () => {
  const productUrl = "http://localhost:8080/api/products";
  // const fetchProduct = () => {
  //   fetch(productUrl)
  //     .then((data) => data.json())
  //     .then((data) => console.log(data));
  // };
  // useEffect(() => {
  //   fetchProduct();
  // }, []);

  document.addEventListener("scroll", () => {
    const top = document.documentElement.scrollTop;
    if (top != 0) {
      document.querySelector(".wrapnav_about").classList.add("sticky");
      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".wrapnav_about").classList.remove("sticky");
    }
  });
  return (
    <div className={cx("wrap")}>
      <div className={cx("commercial")}>
        <Image src={require("../../../assets/commercial.png")} />
      </div>
      <div className="wrapnav_about">
        <div className={cx("wrapNavbar")}>
          <div className={cx("navbar")}>
            <div className={cx("Logo")}>
              <Image src={require("../../../assets/logoGear.jpg")} />
            </div>
            <div className={cx("SearchInput")}>
              <Search placeholder="input search text" enterButton />
            </div>
            <div className={cx("iconAccess")}>
              <UserOutlined style={{ fontSize: 30}} />
              <div className={cx("textAccess")}>
                <h5 className={cx("lblAccess")} >Đăng Nhập</h5>
                <h5 className={cx("lblAccess")}>Đăng Ký</h5>
              </div>
            </div>
            <div className={cx("sale")}>
              <img
                src={require("../../../assets/iconSale.jpg")}
                className={cx("saleLogo")}
              />
              <h5 className={cx("lblAccess")}>Khuyến Mãi</h5>
            </div>
            <Tippy
              interactive
          
              placement="top-end"
              render={attrs => (
              <div className="box" tabIndex="-1" {...attrs}>
                <MenuCard />
                {/* <ItemCard /> */}
              </div>
            )}
            >      
            <div className={cx("cart")}>
              <ShoppingCartOutlined style={{ fontSize: 35 }} />
              <h5 className={cx("lblAccess")}>Giỏ hàng </h5>
            </div>                       
              </Tippy>
          
          </div>
          
          <Nav></Nav>
        </div>
      </div>
    </div>
  );
};

export default Header;
