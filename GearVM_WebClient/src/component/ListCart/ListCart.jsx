import React from 'react'
import  styles  from './ListCart.module.scss'
import classNames from 'classnames/bind'
import Table from 'react-bootstrap/Table';
import { useDispatch,useSelector } from 'react-redux';
import CartSlice from '../../redux/slices/CartSlices';
import { useState } from 'react';
import { useEffect } from 'react';
import ToggleCheckbox from './ToggleCheckbox';
const cx= classNames.bind(styles)
const ListCart = () => {
  const [quantity,setquantity]= useState(1)


  const dispatch=useDispatch();

  const cart=useSelector((state)=>state.todoCart);
 
  console.log(cart.cartItems);

  useEffect(()=>{
    dispatch(CartSlice.actions.totalCart());
  },[cart,dispatch]) 

  const handleAddToCart = (product) => {
    dispatch( CartSlice.actions.addTocart(product));

  };
  const handleSubToCart = (product) => {
    dispatch( CartSlice.actions.subTocart(product));
  
  };
  const handleRemoveCart = (product) => {
    dispatch( CartSlice.actions.removeCart(product));
  
  };
 const  handleChangeQuantity  = (value) => {
    setquantity(value);
  }
  const handleSubmitToCart = ()=>{
    const JcartItems = localStorage.getItem("cartItems");
    const cartItems= JSON.parse(JcartItems)
    // console.log(cartItems[0].checkCart);
    let order=[];
    cartItems.map((item) => {
      if(item.checkCart === true){
        dispatch(CartSlice.actions.removeCart(item));
        order =[...order,item];
      }
    })
    console.log(order);
    
  }
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
      {cart.cartItems && cart.cartItems.map((cartItem) => (  
      <tbody key={cartItem.id}>
        <tr  >
          <td >
            
            <ToggleCheckbox
              cartItem={cartItem}
            />
            
          </td>
          <td>
            <div className={cx('wrapListCart_Content_NameProduct')}>
                <img  src='https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw'
                    className={cx('wrapListCart_Content_img')}
                />
                <div>
                <h5 className={cx('wrapListCart_Content_Name')}>{cartItem.name}</h5>
                {/* <h4 className={cx('wrapListCart_Content_noice')}>Chỉ còn 2 sản phẩm</h4> */}
                </div>
               
           </div>
            </td>
          <td>
            <h5>30.889.000đ</h5>
            <h4 className={cx('wrapListCart_Content_Price')}>{cartItem.price}đ</h4>
            </td>
          <td>
            <div className={cx('wrapListCart_Content_quantity')}>
            <button className={cx('wrapListCart_Content_quantity_btnsub')}
             onClick={() => handleSubToCart(cartItem)}>-</button>
            <input type='text' 
                  value={cartItem.cartQuantity} 
                  onChange={handleChangeQuantity} 
                  className={cx('wrapListCart_Content_quantity_text')}
            />
            <button className={cx('wrapListCart_Content_quantity_btnadd')} 
            onClick={() => handleAddToCart(cartItem)}>
              +
            </button>
            </div>
            <div >
                <button className={cx('wrapListCart_Content_quantity_remove')}
                onClick={() => handleRemoveCart(cartItem)}>Xóa</button>
                </div>
          </td>
          {/* Thành Tiền */}
          <td>  <h5>{cartItem.price*cartItem.cartQuantity}đ</h5></td>
        </tr>
      </tbody>
      )) }
    </Table>
        </div>
        <div className={cx('listCart_Pay')} >
            <h6>Thanh Toán</h6>
            <div >
                <div className={cx('listCart_Pay_content')}>
                    <span className={cx('listCart_Pay_content_text')}>Tổng tạm tính:</span>
                    <span className={cx('listCart_Pay_content_text')}>{cart.cartTotalAmount}₫</span>
                </div>
                <div className={cx('listCart_Pay_content')}>
                    <span className={cx('listCart_Pay_content_text')}>Thành Tiền:</span>
                    <span className={cx('listCart_Pay_content_text_blue')}>{cart.cartTotalAmount}₫</span>
                </div>
            </div>
            <input type="button" value='Thanh Toán'  className={cx('listCart_Pay_content_btn')} onClick={handleSubmitToCart}/>

        </div>
    </div>
  )
}

export default ListCart
