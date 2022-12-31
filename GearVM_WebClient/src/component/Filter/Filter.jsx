import React from 'react'
import styles from './Filter.modulo.scss'
import classNames from 'classnames/bind'

const cx= classNames.bind(styles);

const Filter = () => {
  return (
    <div className={cx('wrapFilter')}>
        <div className={cx('trademark')}>
            <h2>Lọc Theo Thương Hiệu</h2>
            <h4>Acer</h4>
            <h4>Asus</h4>
            <h4>HP</h4>
            <h4>Dell</h4>
            <h4>MSI</h4>
        </div>
        <div className={cx('trademark')}>
            <h2>Lọc Theo Cấu Hình</h2>
            <h4>Intel Core i8</h4>
            <h4>Intel Core i7</h4>
            <h4>Intel Core i6</h4>
            <h4>AMD Ryzen 3</h4>
            <h4>AMD Ryzen 5</h4>
        </div>
        <div className={cx('trademark')}>
            <h2>Lọc Theo Giá</h2>
            <h4>Dưới 10 triệu</h4>
            <h4>10-15 triệu</h4>
            <h4>15-20 triệu</h4>
            <h4>20-25 triệu</h4>
            <h4>25-30 triệu</h4>
        </div>
    </div>
  )
}

export default Filter
