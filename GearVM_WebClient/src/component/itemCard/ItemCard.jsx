import React from 'react'
import styles from'./ItemCard.module.scss'
import classNames from 'classnames/bind'
const cx= classNames.bind(styles)
const ItemCard = () => {
  return (
    <div className={cx('wrapItemCard')}>
            <img 
            className={cx('ItemCard_Img')}
            src='https://betanews.com/wp-content/uploads/2014/11/front.jpg'/>     
        <div className={cx('ItemCard_Content')}>
            <h6 className={cx('txt_ItemCard_Content_Name')}>Acer</h6>
            <h6 className={cx('txt_ItemCard_Content_quantity')}>số lượng: 11</h6>
            <h6 className={cx('txt_ItemCard_Content_price')}>12.000.000</h6>
        </div>
    </div>
    
  )
}

export default ItemCard
