import React, { useState } from 'react'
import styles from './NavModal.modulo.scss'
import classNames from 'classnames/bind'
import {LaptopOutlined } from "@ant-design/icons";
import Filter from '../Filter/Filter';

const cx= classNames.bind(styles);
// const $=document.querySelector.bind(document)
const NavModal = () => {
    const $$=document.querySelectorAll.bind(document)
    const navContent= $$('.NavContent_item');
    console.log(navContent);
    const [openFilter,setopenFilter] =useState(false)
    navContent.forEach((nav,index) => {
        nav.onclick=()=>{
           
            setopenFilter(!openFilter);
        }
    });
   
  return (
    <div className={cx('wrapNavModal')}>
            <div className= {cx('NavContent')}>
                <div className= {cx('NavContent_item active')}>
                    <LaptopOutlined  style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>Laptop</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined  style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>Laptop gaming</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>PC special</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>Màn hình</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>Bàn phím</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>Chuột</span>
                </div>
                <div className= {cx('NavContent_item')}>
                    <LaptopOutlined style={{fontSize:20}}/>
                    <span style={{fontSize:18,marginLeft:15}}>PC doanh nghiệp</span>
                </div>
            
            </div>
            {/* {openFilter && <Filter></Filter>} */}
            {/* <Filter></Filter> */}
            
    </div>
  )
}

export default NavModal
