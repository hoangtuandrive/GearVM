import React,{useState,useEffect,useCallback} from 'react'
import styles from'./CatalogProduct.module.scss'
import classNames from 'classnames/bind'
import ItemProduct from '../../itemProduct/ItemProduct'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import CheckBox from '../CusCheckbox/CheckBox';
const cx=classNames.bind(styles)
const CatalogProduct = () => {
    const productList=[{
        id:1,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 1',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Đỏ", "Xanh Dương"],
      },
      {
        id:2,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 2',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Đỏ", "Xanh Dương"],

      },{
        id:3,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 3',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Đỏ", "Cam"],

      },{
        id:4,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 4',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Đỏ", "Xanh Dương"],

      },{
        id:5,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 5',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Vàng", "Xanh Dương"],

      },
      ,{
        id:6,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 6',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Đen", "Xanh Dương"],

      },{
        id:7,
        img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
        name:'Acer 7',
        price:'12.000.000',
        discount:'4',
        colors: ["Trắng", "Hồng", "Xanh Dương"],

      }]
    const colors = [
        {
            color: "Trắng",   
        },
        {
          color: "Hồng",
        },
        {
           color: "Đen",        
        },
        {
            color: "Vàng",       
        },
        {
            color: "Cam",      
        },
        {
            color: "Xanh dương",
        }
    ]
    const initFilter = {
      color: [],
  }
  
  const [products, setProducts] = useState(productList)

  const [filter, setFilter] = useState(initFilter)

  const filterSelect = (type, checked, item) => {
      if (checked) {
          switch(type) {
              case "COLOR":
                  setFilter({...filter, color: [...filter.color, item.color]})
                  break
              default:
          }
         
      } else {
          switch(type) {
              case "COLOR":
                  const newColor = filter.color.filter(e => e !== item.color)
                  setFilter({...filter, color: newColor})
                  break
              default:
          }
      }
  }
  const updateProducts = useCallback(
    () => {
        let temp = productList
        if (filter.color.length > 0) {
            temp = temp.filter(e => {
                const check = e.colors.find(color => filter.color.includes(color))
                console.log(check)
                return check !== undefined        
            })
        }
        setProducts(temp)
    },
    [filter, productList],
)
console.log(products)
useEffect(() => {
  updateProducts()
}, [filter])
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
               <div className='gach'></div>
               
               <div  className={cx('wrapCatalogProduct_fillter_Price')}>
                  <h6 className={cx('txtWrap_Head')}>Màu Sắc</h6>
                  <div className={cx('wrapCatalogProduct_fillter_Price_about')}>
                    {/* <h5 className={cx('txtWrap')}>Xanh</h5>
                    <h5 className={cx('txtWrap')}>Đỏ</h5>
                    <h5 className={cx('txtWrap')}>Tím</h5>
                    <h5 className={cx('txtWrap')}>Vàng</h5> */}
                    {
                                colors.map((item, index) => (
                                    <div key={index} className={cx("catalog__filter__widget__content__item")}>
                                        <CheckBox
                                            label={item.color}
                                            onChange={(input) => filterSelect("COLOR", input.checked, item)}
                                            checked={filter.color.includes(item.color)}
                                        />
                                    </div>
                                ))
                            }
                  </div>
               </div>


        </div>
        <div className={cx('wrapCatalogProduct_content')}> 
        <Container>
            <Row md={4}>     
                {
                    products.map((item, index) => (
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
