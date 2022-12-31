import React from 'react'
import classNames from 'classnames/bind'
import styles from './ItemProduct.modulo.scss'
import { Image } from 'antd';

const cx= classNames.bind(styles);
const ItemProduct = (props) => {

  return (
    <div className={cx('wrapItemProduct')}>
        <div  className={cx('imgProduct')}>
            <Image       
            src={props.data.img}
            />
        </div>   
        <div className={cx('textProduct')}>
            <h5>{props.data.name}</h5>
          <div className={cx('content_dis_price')}> 
            <h5 className={cx('Price')}>{props.data.price}đ</h5>
            <h5>{props.data.discount}%</h5>
          </div>
          <h5>Giá Thật</h5>
        </div>   
    </div>
  )
}

export default ItemProduct
