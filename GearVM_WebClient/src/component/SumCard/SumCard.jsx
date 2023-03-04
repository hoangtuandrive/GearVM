import React from 'react'
import styles from'./SumCard.module.scss'
import classNames from 'classnames/bind'
import { useNavigate } from 'react-router-dom'
import { useSelector } from 'react-redux'
const cx= classNames.bind(styles)

const SumCard = () => {
  const cart=useSelector((state)=>state.todoCart);

  const navigate= useNavigate();
  const handelPageCart =()=>{
    navigate('/cart', { replace: true }); 
  }
  return (
    <div className={cx('wrapSumCard')}>
        <div className={cx('wrapSumCard_Content')}>
            <h5>Tổng tiền({cart.cartItems.length}) sản phẩm</h5>
            <h5>{cart.cartTotalAmount}đ</h5>
        </div>
        <input type="button" value='Xem Giỏ Hàng'  className={cx('wrapSumCard_Button')}
          onClick={handelPageCart}
        />
    </div>
  )
}

export default SumCard
