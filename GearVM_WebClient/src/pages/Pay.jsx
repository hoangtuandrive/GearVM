import React from 'react'
import Header from '../component/Home/header/Header'
import Footer from '../component/Home/footer/Footer'
import  styles  from '../sass/Pay.module.scss'
import classNames from 'classnames/bind'
import PayBody from '../component/Paybody/PayBody'

const cx=classNames.bind(styles);
const Pay = () => {
  return (
    <div className={cx('wrapPay')}>
      <Header />
      <PayBody />
      <Footer />
    </div>
  )
}

export default Pay
