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
            src={'https://betanews.com/wp-content/uploads/2014/11/front.jpg'}
            />
        </div>   
        <div className={cx('textProduct')}>
            <h5  className={cx('txtNameProduct')}>{props.data.name}</h5>
          <div className={cx('content_dis_price')}> 
            <h5 className={cx('txtPrice')}>{props.data.price}đ</h5>
            <div className={cx('contentDiscount')}>
            <h5 className={cx('txtDiscount')}>{props.data.discount}%</h5>
            </div>
          </div>
          <h5 className={cx('txt_pricereal')}>{props.data.price}đ</h5>
        </div>   
    </div>
  )
}

export default ItemProduct
