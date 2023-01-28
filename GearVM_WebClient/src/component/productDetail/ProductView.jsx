import React from 'react'
import  styles  from './ProductView.module.scss'
import classNames from 'classnames/bind'
import CustomDiscount from '../customDiscount/CustomDiscount'
import { useNavigate,useLocation} from 'react-router-dom'
import { useDispatch } from 'react-redux'
import CartSlice from '../../redux/slices/CartSlices'
const cx= classNames.bind(styles)

const ProductView = () => {
  const product={
    id:1,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  }
 
  const dispatch=useDispatch();
  const handleAddToCart = (product) => {
    dispatch(CartSlice.actions.addTocart(product))
  };

  let location=useLocation();
  let query=new URLSearchParams(location.search)
  console.log(query.get("name"))
  return (
    <div className={cx('wrapProductView')}>
        <div className={cx('ProductView_Img')}>
            <img src='https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw'
             className={cx('ProductView_Img_main')} />
            <div className={cx('ProductView_Img_list')}>
                <img  
                 src='https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw'
                 className={cx('ProductView_Img_itemImg')}
                />
                <img  
                 src='https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw'
                 className={cx('ProductView_Img_itemImg')}
                />
                <img  
                 src='https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw'
                 className={cx('ProductView_Img_itemImg')}
                />
                <img  
                 src='https://lh3.googleusercontent.com/n3SmM0qTA-hYAcUnll-AQZR84ICPNF7fMPrVezf6O6uNloFGE5MTMs1JknYjarchgumi-ZVxzPRfjEjLVcT89a62nA096vbIzA=rw'
                 className={cx('ProductView_Img_itemImg')}
                />
               
            </div>         
        </div>
        <div className={cx('ProductView_Content')}>
            <h1 className={cx('ProductView_txtName')}>Laptop ASUS Vivobook X515EA-BQ2351W (i3-1115G4/RAM 4GB/512GB SSD/ Windows 11)</h1>
            <div className={cx('ProductView_Content_origin')}>
                <h4 className={cx('ProductView_txtTrademark')}>
                  Thương hiệu
                  <span className={cx('ProductView_txtTrademark_Name')}>  Asus</span>
                </h4>
                <h4 className={cx('ProductView_txtTrademark')}>
                  Mã sản phẩm:
                  <span>  141</span>
                </h4>
            </div>
            <h5 className={cx('ProductView_txtPrice')} >10.490.000₫</h5>
            <div className={cx('ProductView_Discount')}>
              <h5 className={cx('ProductView_txtPrice_dis')}>
                13.990.000₫
              </h5>
              <div className={cx('contentDiscount')}>
                    <h5 className={cx('txtDiscount')}>
                     4%
                    </h5>
              </div>
            </div>
            <CustomDiscount />
            <div className={cx('ProductView_btn')}>
                <input type='button' value='Mua Ngay'  className={cx('ProductView_btn_Buy')}/>
                <input type='button' value='Thêm Vào Giỏ Hàng'  className={cx('ProductView_btn_Addcard')} 
                onClick={() => handleAddToCart(product)}/>

            </div>
        </div>
    </div>
  )
}

export default ProductView
