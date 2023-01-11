import React from 'react'
import  styles  from './CustomDiscount.module.scss'
import classNames from 'classnames/bind'
const cx= classNames.bind(styles)
const CustomDiscount = () => {
  return (
    <div className={cx('wrapCustomDiscount')}>
        <div className={cx('wrapCustomDiscount_img')}>
            <img src={require('../../assets/gift.png')} />
        </div>
        <div className={cx('wrapCustomDiscount_content')}>
            <h6>Miễn phí vẫn chuyển</h6>
            <h5>Nhân dịp khai chương cửa hàng</h5>
            <h5 className={cx('wrapCustomDiscount_content_txt')}>Có hiệu lực 05/01/2023</h5>
        </div>
    </div>
  )
}

export default CustomDiscount
