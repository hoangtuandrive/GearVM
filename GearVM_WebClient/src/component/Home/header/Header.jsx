import React from 'react'
import styles from "./Header.modulo.scss";
import classNames from "classnames/bind";   
import { Image,Input } from 'antd';
import {UserOutlined,ShoppingCartOutlined } from "@ant-design/icons";
import Nav from '../nav/nav';
// const {  AudioOutlined  } = icons;
const { Search } = Input;
const cx = classNames.bind(styles);
const Header = () => {
  return (
    <div className={cx('wrap')}>
        <div className={cx('commercial')}>
            <Image src={require('../../../assets/commercial.png')}/>
        </div>
        
        <div className={cx('wrapNavbar')}> 

        <div className={cx('navbar')}>
            <div className={cx('Logo')}>
                <Image src={require('../../../assets/logoGear.jpg')} />
            </div>
            <div className={cx('SearchInput')}>
            <Search placeholder="input search text"  enterButton />
            </div>
            <div className={cx('iconAccess')}>
                <UserOutlined style={{fontSize:35}} />
                <div className={cx('textAccess')}>
                    <h5>Đăng Nhập</h5>
                    <h5>Đăng Ký</h5>
                </div>
              
            </div>
            <div className={cx('sale')}>        
                    <img src={require('../../../assets/iconSale.jpg')}
                     className={cx('saleLogo')} />
                <h5>Khuyến Mãi</h5>
            </div>
            <div className={cx('cart')}>
                 <ShoppingCartOutlined style={{fontSize:35}} />
                 <h5>Giỏ Hàng</h5>
            </div>
        </div>

            <Nav></Nav>
        
        </div>

    </div>
  )
}

export default Header
