import React from 'react'
import styles from'./MenuCard.module.scss'
import classNames from 'classnames/bind'
import ItemCard from '../itemCard/ItemCard'
import SumCard from '../SumCard/SumCard'
import { useSelector } from 'react-redux'

const cx= classNames.bind(styles)
const MenuCard = () => {
  const cart=useSelector((state)=>state.todoCart);
  console.log(cart);
  return (
      <div className={cx('wrapCard')}>
        <div className={cx('wrapCard_Content')}>
          {cart.cartItems.map((item)=>(
              <ItemCard 
              key={item.id}
              data={item}
              />
            
          ))}
       
        </div>
          <SumCard />
    </div> 
  )
}

export default MenuCard
