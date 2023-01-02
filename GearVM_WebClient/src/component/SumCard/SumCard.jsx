import React from 'react'
import styles from'./SumCard.module.scss'
import classNames from 'classnames/bind'

const cx= classNames.bind(styles)

const SumCard = () => {
  return (
    <div className={cx('wrapSumCard')}>
        <div className={cx('wrapSumCard_Content')}>
            <h5>Tổng tiền(3) sản phẩm</h5>
            <h5>36.175.000đ</h5>
        </div>
        <input type="button" value='Xem Giỏ Hàng'  className={cx('wrapSumCard_Button')}/>
    </div>
  )
}

export default SumCard
