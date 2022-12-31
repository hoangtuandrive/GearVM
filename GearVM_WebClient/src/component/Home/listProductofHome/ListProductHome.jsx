import React from 'react'
import classNames from 'classnames/bind'
import styles from './ListProductHome.modulo.scss'
import ListProduct from '../../ListProduct/ListProduct'
const cx= classNames.bind(styles)
const ListProductHome = () => {
    const Laptop=[{
        id:1,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },
      {
        id:2,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:3,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:4,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:5,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },
      ,{
        id:6,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      }]
      const BanPhim=[{
        id:1,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },
      {
        id:2,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:3,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:4,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },{
        id:5,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      },
      ,{
        id:6,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      }]
  return (
    <div className={cx('wrapListHome')} >
        <div className={cx('WrapListProduct_Laptop')} >
            <h1 className={cx('lblwraplistproduct')}>Laptop</h1>
            <ListProduct data={Laptop} />
        </div>
        <div className={cx('WrapListProduct_BanPhim')} >
            <h1 className={cx('lblwraplistproduct')}>Bàn Phím</h1>
            <ListProduct data={BanPhim} />
        </div>
    </div>
  )
}

export default ListProductHome
