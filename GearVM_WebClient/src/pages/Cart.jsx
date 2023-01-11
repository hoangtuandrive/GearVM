import React from 'react'
import Header from '../component/Home/header/Header'
import Footer from '../component/Home/footer/Footer'
import ProductView from '../component/productDetail/ProductView'
import  styles  from '../sass/Cart.module.scss'
import classNames from 'classnames/bind'
import ListCart from '../component/ListCart/ListCart'
const cx= classNames.bind(styles)
const Cart = () => {
  return (
    <div className={cx('wrapCart')}>
     <Header />
      <ListCart />
      <Footer />
    </div>
  )
}

export default Cart
