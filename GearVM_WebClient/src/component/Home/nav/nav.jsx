import React from 'react'
import styles from './nav.modulo.scss'
import classNames from 'classnames/bind'
import {MenuOutlined  } from "@ant-design/icons";
const cx= classNames.bind(styles);
const nav = () => {
  return (
    <div className='navwrap'>
        <div className={cx('ListProduct')}>
            <MenuOutlined  />
            <h4 className={cx('textItemProduct')}>Danh Mục Sản Phẩm</h4>
        </div>
        <div className={cx('itemProduct')}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Hướng dẫn Thanh Toán</h4>
        </div>
        <div className={cx('itemProduct')}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Hướng dẫn trả góp</h4>
        </div>
        <div className={cx('itemProduct')}>
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

export default nav
