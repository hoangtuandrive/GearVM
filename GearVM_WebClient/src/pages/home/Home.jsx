import React from 'react'
import styles from './home.modulo.scss'
import classNames from 'classnames/bind'
import Header from '../../component/Home/header/Header'

import SlideShow from '../../component/Home/slideShow/SlideShow'
import NavModal from '../../component/Modal/NavModal'
import ListProduct from '../../component/ListProduct/ListProduct'
import Footer from '../../component/Home/footer/Footer'
const cx=classNames.bind(styles)
const Home = () => {
  return (
    <div className={cx('wrapHome')}>
      <Header></Header>
      {/* <NavModal/> */}
      <SlideShow />
      <ListProduct />
      <Footer />
    </div>
  )
}

export default Home
