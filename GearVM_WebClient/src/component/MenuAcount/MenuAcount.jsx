import React, { useContext } from 'react'
import styles from './MenuAcount.module.scss'
import {LaptopOutlined } from "@ant-design/icons";
import classNames from 'classnames/bind'
import { useNavigate } from 'react-router-dom';
import { AppContext } from '../context/AppProvider';
import ModalUser from '../ModalUser/ModalUser';
const cx=classNames.bind(styles);
const MenuAcount = () => {
  const {setUserOpen} =useContext(AppContext)
  const navigate= useNavigate();
    const handleOrder= ()=>{ navigate("/orderManager", { replace: true });}
    const handleOpenUser= ()=>{ setUserOpen(true)}

  return (
    <div className={cx('wrapMenuAcount')}>
       <div className={cx('wrapMenuAcount_thumb')} onClick={handleOpenUser} >
          <LaptopOutlined />  
          <p className={cx('wrapMenuAcount_thumb_txt')}>Thông tin tài khoản</p>
       </div>
       <div className={cx('wrapMenuAcount_thumb')} onClick={handleOrder}>
          <LaptopOutlined />  
          <p className={cx('wrapMenuAcount_thumb_txt')}>Quản lý đơn hàng</p>
       </div>
       <div className={cx('wrapMenuAcount_thumb')}>
          <LaptopOutlined />  
          <p className={cx('wrapMenuAcount_thumb_txt')}>Số địa chỉ</p>
       </div>
       <input type='button' className={cx('wrapMenuAcount_thumb_btn')} value='Đăng xuất'/>
       <ModalUser />
    </div>
  
  )
}

export default MenuAcount
