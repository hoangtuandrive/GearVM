import React from 'react'
import styles from'./Acount.module.scss'
import classNames from 'classnames/bind'
const cx=classNames.bind(styles)
const Acount = () => {
  return (
    <div className={cx('wrapAcount')}>
      <img src='https://i.pinimg.com/originals/dd/0e/86/dd0e86cc6c9ae4bc0473c762e275f9c4.jpg'
      className={cx('wrapAcount_img')}
      />
        <div className={cx('wrapAcount_thumb')} >
            <p className={cx('wrapAcount_thumb_txt')}>Xin chào,</p>
            <p className={cx('wrapAcount_thumb_txt')}>Hoàng Long</p>
        </div>
    </div>
  )
}

export default Acount
