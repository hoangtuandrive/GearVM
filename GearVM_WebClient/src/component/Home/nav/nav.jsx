import React from 'react'
import styles from './nav.modulo.scss'
import classNames from 'classnames/bind'
import {MenuOutlined  } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
const cx= classNames.bind(styles);
const Nav = () => {
    const navigate = useNavigate();
    const PageProduct= ()=>{
        navigate("/catalog", { replace: true });
    }
    const PageProductDetail= ()=>{
        navigate("/productdetail", { replace: true });
    }
    const Pagecart= ()=>{
        navigate("/cart", { replace: true });
    }
  return (
    <div className='navwrap'>
        <div className={cx('ListProduct')}>
            <MenuOutlined  />
            <h4 className={cx('textItemProduct')}>Danh Mục Sản Phẩm</h4>
        </div>
        <div className={cx('itemProduct')} onClick={PageProduct}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')} >Hướng dẫn Thanh Toán</h4>
        </div>
        <div className={cx('itemProduct')} onClick={PageProductDetail}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Hướng dẫn trả góp</h4>
        </div>
        <div className={cx('itemProduct')} onClick={Pagecart}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Chính sách bảo hành</h4>
        </div>
        <div className={cx('itemProduct')}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Chính sách vận chuyển</h4>
        </div>
    </div>
  )
}

export default Nav
