import React from 'react'
import  styles  from './ListCart.module.scss'
import classNames from 'classnames/bind'
import Table from 'react-bootstrap/Table';
import CheckBox from '../Catalog/CusCheckbox/CheckBox';
import { Checkbox } from 'antd';
import { useDispatch,useSelector } from 'react-redux';
import CartSlice from '../../redux/slices/CartSlices';

const cx= classNames.bind(styles)
const ListCart = () => {
  const product={
    id:1,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  }
 
  const dispatch=useDispatch();
  const cart=useSelector((state)=>state.todoCart);
 
  const handleAddToCart = (product) => {
    dispatch( CartSlice.actions.addTocart(product));
    console.log(cart);
  };
  const handleSubToCart = (product) => {
    dispatch( CartSlice.actions.subTocart(product));
    console.log(cart);
  };
  const handleRemoveCart = (product) => {
    dispatch( CartSlice.actions.removeCart(product));
    console.log(cart);
  };
  return (
    <div className={cx('wrapListCart')}>
        <div className={cx('wrapListCart_Content')}>
        <Table >
      <thead>
        <tr >
          <th>Chọn</th>
          <th>
            <h5 className={cx('wrapListCart_Content_tr_text')}>Tên sản phẩm</h5>
        </th>
          <th>Đơn giá</th>
          <th>Số lượng</th>
          <th>Thành tiền</th>
        </tr>
      </thead>
      <tbody>
        <tr  >
          <td><Checkbox /></td>
          <td>
            <div className={cx('wrapListCart_Content_NameProduct')}>
                <img  src='https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw'
                    className={cx('wrapListCart_Content_img')}
                />
                <div>
                <h5 className={cx('wrapListCart_Content_Name')}>Acer</h5>
                <h4 className={cx('wrapListCart_Content_noice')}>Chỉ còn 2 sản phẩm</h4>
                </div>
               
           </div>
            </td>
          <td>
            <h5>30.889.000đ</h5>
            <h4 className={cx('wrapListCart_Content_Price')}>30.889.000đ</h4>
            </td>
          <td>
            <div className={cx('wrapListCart_Content_quantity')}>
            <button className={cx('wrapListCart_Content_quantity_btnsub')}
             onClick={() => handleSubToCart(product)}>-</button>
            <input type='text' defaultValue={1} className={cx('wrapListCart_Content_quantity_text')}/>
            <button className={cx('wrapListCart_Content_quantity_btnadd')} 
            onClick={() => handleAddToCart(product)}>
              +
            </button>
            </div>
            <div >
                <button className={cx('wrapListCart_Content_quantity_remove')}
                onClick={() => handleRemoveCart(product)}
                >Xóa</button>
                </div>
          </td>
          <td>  <h5>30.889.000đ</h5></td>
        </tr>
      </tbody>
      
    </Table>
        </div>
        <div className={cx('listCart_Pay')} >
            <h6>Thanh Toán</h6>
            <div >
                <div className={cx('listCart_Pay_content')}>
                    <span className={cx('listCart_Pay_content_text')}>Tổng tạm tính:</span>
                    <span className={cx('listCart_Pay_content_text')}>75.768.000₫</span>
                </div>
                <div className={cx('listCart_Pay_content')}>
                    <span className={cx('listCart_Pay_content_text')}>Thành Tiền:</span>
                    <span className={cx('listCart_Pay_content_text_blue')}>75.768.000₫</span>
                </div>
            </div>
            <input type="button" value='Thanh Toán'  className={cx('listCart_Pay_content_btn')}/>

        </div>
    </div>
  )
}

export default ListCart
