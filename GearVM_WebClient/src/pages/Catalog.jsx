import React from 'react'
import Header from '../component/Home/header/Header'
import Footer from '../component/Home/footer/Footer'
import  styles  from '../sass/Catalog.modulo.scss'
import classNames from 'classnames/bind'
import CatalogProduct from '../component/Catalog/catalogProduct/CatalogProduct'
const cx= classNames.bind(styles)
const Catalog = () => {
  return (
    <div className={cx('wrapCatalog')}>
      <Header/>
      <CatalogProduct />
      <Footer />
    </div>
  )
}

export default Catalog

