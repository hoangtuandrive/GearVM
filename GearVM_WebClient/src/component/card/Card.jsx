import React from 'react'
import styles from'./MenuCard.module.scss'
import classNames from 'classnames/bind'
import ItemCard from '../itemCard/ItemCard'
import SumCard from '../SumCard/SumCard'


const cx= classNames.bind(styles)
const MenuCard = () => {
  return (
      <div className={cx('wrapCard')}>
        <div className={cx('wrapCard_Content')}>
          <ItemCard />
        </div>
          <SumCard />
    </div> 
  )
}

export default MenuCard
