import React from 'react'
import styles from'./CatalogProduct.modulo.scss'
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
                <h2>  Danh mục sản phẩm</h2>
        </div>
        <div className={cx('wrapCatalogProduct_content')}> 
        <Container>
            <Row md={3}>     
                {
                    Laptop.map((item, index) => (
                      <Col >
                        <ItemProduct
                        key={item.id}
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
