import React from 'react'
import { Checkbox } from 'antd';
import { useDispatch,useSelector } from 'react-redux';
import CartSlice from '../../redux/slices/CartSlices';
// import { useEffect } from 'react';

const ToggleCheckbox = ({cartItem}) => {
    const dispatch=useDispatch();
//   const cart=useSelector((state)=>state.todoCart);

    const toggleCheckbox = () => {
        console.log(cartItem)
        dispatch(CartSlice.actions.ChangeCheckCart(cartItem))
        // console.log(checkCart);

      };
  return (
    <div>
        <Checkbox checked={cartItem.checkCart}  onChange={toggleCheckbox}></Checkbox>
    </div>
  )
}

export default ToggleCheckbox
