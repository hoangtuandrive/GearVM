import React from 'react'
import styles from './nav.modulo.scss'
import classNames from 'classnames/bind'
import {MenuOutlined  } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { Row, Col } from 'react-bootstrap';

const cx= classNames.bind(styles);
const Nav = () => {
    const navigate = useNavigate();
    const PageGuidePayment= ()=>{
        navigate("/guidePayment", { replace: true });
    }
    const PageGuidePolicy= ()=>{
        navigate("/guidePolicy", { replace: true });
    }
    const PageGuideDeli= ()=>{
        navigate("/guideDeli", { replace: true });
    }
  return (
      <div className='navwrap'>
        <Row>
            <Col sm={3}>
        <div className={cx('ListProduct')}>
            <MenuOutlined />
            <h4 className={cx('textItemProduct')}>Danh Mục Sản Phẩm</h4>
        </div>
            </Col>
            <Col sm={3}>
            <div className={cx('itemProduct')} onClick={PageGuidePayment}>
            <MenuOutlined />
          
          
          <h4 className={cx('textItemProduct')} >Hướng dẫn Thanh Toán</h4>
      </div>
            </Col>
            <Col sm={3}>
            <div className={cx('itemProduct')} onClick={PageGuidePolicy}>
            <MenuOutlined />
         
         <h4 className={cx('textItemProduct')}>Chính sách bảo hành</h4>
     </div>
            </Col>
            <Col sm={3}>
            <div className={cx('itemProduct')}  onClick={PageGuideDeli}>
            <MenuOutlined />
         
         <h4 className={cx('textItemProduct')}>Chính sách vận chuyển</h4>
     </div>
            </Col>
        </Row>
    </div>
  )
}

export default Nav
