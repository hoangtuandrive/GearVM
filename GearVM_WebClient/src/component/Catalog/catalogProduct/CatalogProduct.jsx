import React from 'react'
import styles from'./CatalogProduct.module.scss'
import classNames from 'classnames/bind'
import ItemProduct from '../../itemProduct/ItemProduct'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const cx=classNames.bind(styles)
const CatalogProduct = () => {
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
      },{
        id:7,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer',
        price:'12.000.000',
        discount:'4'
      }]
  return (
    <div className={cx('wrapCatalogProduct')}>
        <div className={cx('wrapCatalogProduct_fillter')}>
               <div  className={cx('wrapCatalogProduct_fillter_Price')}>
                  <h6 className={cx('txtWrap_Head')}>Khoảng giá</h6>
                  <div className={cx('wrapCatalogProduct_fillter_Price_about')}>
                    <h5 className={cx('txtWrap')}>Dưới 10 triệu</h5>
                    <h5 className={cx('txtWrap')}>10-15 triệu</h5>
                    <h5 className={cx('txtWrap')}>15-20 triệu</h5>
                    <h5 className={cx('txtWrap')}>Trên 20 triệu</h5>
                  </div>
               </div>
               <div className='gach'>
                {/* <CheckBox /> */}
               </div>
               <div  className={cx('wrapCatalogProduct_fillter_Price')}>
                  <h6 className={cx('txtWrap_Head')}>Màu Sắc</h6>
                  <div className={cx('wrapCatalogProduct_fillter_Price_about')}>
                    <h5 className={cx('txtWrap')}>Xanh</h5>
                    <h5 className={cx('txtWrap')}>Đỏ</h5>
                    <h5 className={cx('txtWrap')}>Tím</h5>
                    <h5 className={cx('txtWrap')}>Vàng</h5>
                  </div>
               </div>


        </div>
        <div className={cx('wrapCatalogProduct_content')}> 
        <Container>
            <Row md={4}>     
                {
                    Laptop.map((item, index) => (
                      <Col key={item.id}>
                      
                        <ItemProduct
                      
                          data={item}
                        />
                      
                        </Col> 
                    ))
                }
            
              </Row>
            </Container>
        </div>
    </div>
  )
}

export default CatalogProduct
