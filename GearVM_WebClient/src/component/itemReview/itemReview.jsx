import React from 'react'
import styles from './itemReview.module.scss'
import classNames from 'classnames/bind'
import { Avatar } from 'antd';
import ReactStars from "react-rating-stars-component";
const cx= classNames.bind(styles);
const ItemReview = () => {
  
  return (
    <div className={cx('wrapitemReview')}>
        <div className={cx('reviewAvatar')}>
                <Avatar  
                className={cx('reviewAvatar_img')}
                />
                <span>Trần Hoàng Long</span>
        </div>
        <div className={cx('reviewRate')}>
        <ReactStars
          count={5}
          value={1.5}
          size={24}
          isHalf={true}
          emptyIcon={<i className="far fa-star"></i>}
          halfIcon={<i className="fa fa-star-half-alt"></i>}
          fullIcon={<i className="fa fa-star"></i>}
          activeColor="#ffd700"
        />
                <p>Giao hàng nhanh</p>
        </div>
    </div>
  )
}

export default ItemReview

