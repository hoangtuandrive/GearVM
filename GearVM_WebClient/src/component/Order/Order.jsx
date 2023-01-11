    import React from 'react'
    import styles from './Order.module.scss'
    import classNames from 'classnames/bind'   
    import { Button,Checkbox } from 'antd'
    import Table from 'react-bootstrap/Table';
    const cx=classNames.bind(styles)
    const Order = () => {
    return (
        <div className={cx('wrapOrder')}>
        <div className={cx('wrapOrder_thumb')}>
                <h5>
                    Quản Lý Đơn Hàng
                </h5>
                <div>
                    <Button type="primary" >Chờ Thanh Toán</Button>
                    <Button>Chờ Giao Hàng</Button>
                    <Button>Đã Hoàn Thành</Button>
                </div>
        </div>
        {/* <div className={cx('wrapOrder_content')}> */}
        <div className={cx('wrapListCart_Content')}>
            <Table >
        <thead>
            <tr >
            <th>
                <h5 className={cx('wrapListCart_Content_tr_text')}>Tên sản phẩm</h5>
            </th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <td>
                <div className={cx('wrapListCart_Content_NameProduct')}>
                    <img  src='https://lh3.googleusercontent.com/skwj0sp9gWzzKtL3cuFtE7kncj6bDcdGfezZpM6WByG8MUAykq_97iN5EzZefQVDPJrrQOaE5yvOsRMKXEup3N7qOoRJpK4p_A=rw'
                        className={cx('wrapListCart_Content_img')}
                    />
                    <div>
                    <p className={cx('wrapListCart_Content_Name')}>Máy tính xách tay/ Laptop Asus Vivobook X1502ZA-BQ127W (i5-1240P) (Xanh)</p>
                    
                    </div>     
            </div>
                </td>
            <td >
                <h5>30.889.000đ</h5>
                <h4 className={cx('wrapListCart_Content_Price')}>30.889.000đ</h4>
                </td>
            <td>
                <p className={cx('wrapListCart_Content_quantiy')}>1</p>
            </td>
            <td>  <h5>30.889.000đ</h5></td>
            </tr>
        </tbody>
        </Table>
            </div>
        </div>
    
        // </div>
    )
    }

    export default Order
