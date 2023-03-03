import React from 'react'
import styles from'./SumCard.module.scss'
import classNames from 'classnames/bind'
import { useNavigate } from 'react-router-dom'
const cx= classNames.bind(styles)

const SumCard = () => {
  const navigate= useNavigate();
  const handelPageCart =()=>{
    navigate('/cart', { replace: true }); 
  }
  return (
    <div className={cx('wrapSumCard')}>
        <div className={cx('wrapSumCard_Content')}>
            <h5>Tổng tiền(3) sản phẩm</h5>
            <h5>36.175.000đ</h5>
        </div>
        <input type="button" value='Xem Giỏ Hàng'  className={cx('wrapSumCard_Button')}
          onClick={handelPageCart}
        />
    </div>
  )
}

export default SumCard
