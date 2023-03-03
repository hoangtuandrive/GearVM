import React from 'react'
import styles from './Promotion.modulo.scss'
import classNames from 'classnames/bind'
import Header from '../../component/Home/header/Header';
import Footer from '../../component/Home/footer/Footer';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import SlideShow from '../../component/Home/slideShow/SlideShow';
import { Button } from 'antd';

const cx= classNames.bind(styles);

const Promotion = () => {
  return (
    <div>
      <Header />
      <Container>
        <Row>
          <Col lg={12}>
            <SlideShow></SlideShow>
          </Col>
        </Row>
        <Row className={cx('promo-content')}>
          <Col lg={6}  className={cx('promo-title')} >
            <Row>

            <img src='https://lh3.googleusercontent.com/H12CnWwDLkgfRerTTwPF-Tme7dyGyLTOy-YaekdN-oXBHDLOMb26gpF90FkqV_BIiBmZtsh2JQ1MPInFDLsnobw1XPU4oC9HQQ=rw-w1090' 
            className={cx('promo-img')}
            />
            </Row>
            <h3>Thiết Bị Đa Năng - Tăng Nhanh Hiệu Suất</h3>
            <Button>Xem Chi Tiết</Button>
           </Col>
           <Col lg={6} className={cx('promo-title')}>
            <Row>

           <img src='https://lh3.googleusercontent.com/mybYEzOt3ET2PxurciM0eRF-uUah2b79GhsleyRk-helWw8VMXLsZAMlXvgAcFbMrBLy6Pfvs9rCALVrMu9ROhRRTzrpdl3N=rw-w1090' 
               className={cx('promo-img')}
           />
            </Row>
            <h3>Mở Rộng Tầm Nhìn - Màn Hình Chỉ Từ 2.6 Triệu</h3>
            <Button>Xem Chi Tiết</Button>
           </Col>
        </Row>
        <Row className={cx('promo-content')}>
          <Col lg={6}  className={cx('promo-title')} >
            <Row>


            <img src='https://lh3.googleusercontent.com/Z_c4Gfhx8sINJj4t1r32UC6og6MoGPFMVE-N3sUpmLU8Fi3U1ZXa__tx-NkIrIwf7kNdZAzXfifYm4D8xlhtWoQ3n7GjPThk=rw-w1090' 
            className={cx('promo-img')}
            />
            </Row>
            <h3>Cấu Hình Xịn - Chiến Game Đỉnh</h3>
            <Button>Xem Chi Tiết</Button>
           </Col>
           <Col lg={6} className={cx('promo-title')}>
            <Row>
           <img src='https://lh3.googleusercontent.com/H12CnWwDLkgfRerTTwPF-Tme7dyGyLTOy-YaekdN-oXBHDLOMb26gpF90FkqV_BIiBmZtsh2JQ1MPInFDLsnobw1XPU4oC9HQQ=rw-w1090' 
               className={cx('promo-img')}
           />
            </Row>
            <h3>Loa Hay Mic Rõ</h3>
            <Button>Xem Chi Tiết</Button>
           </Col>
        </Row>
      </Container>
      <Footer />
    </div>
  )
}

export default Promotion
