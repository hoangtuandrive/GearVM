import React from 'react';
import styles from './GuideDeli.modulo.scss'
import classNames from 'classnames/bind'
import Header from '../../component/Home/header/Header';
import Footer from '../../component/Home/footer/Footer';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const cx=classNames.bind(styles);
const guidedeli = () => {
  return (
    <div>
        <Header />
        <div className={cx('wrapdeli')}>
        <Container>
            <Row>
                <Col lg={6} className={cx('introleft')}>
                  <h1 style={{fontSize:40,fontWeight:'bold'}}>Chính Sách</h1>
                  <h1 style={{fontSize:70,fontWeight:'bold',color:'red',width:500}}>Giao Hàng</h1>
                  <div className={cx('introleftimg')}>
                      <img src='https://w.ladicdn.com/s700x550/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-32-32-20210129103739.png' 
                      style={{height:'20vh'}}
                            /> 
                      <img  src='https://w.ladicdn.com/s550x550/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-04-20210127043537.png'
                      style={{height:'30vh'}}
                      />
                  </div>
                </Col>
                <Col lg={6} className={cx('Deliimg')}>
                  <img src='https://w.ladicdn.com/s850x700/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-34-20210129105256.png' 
                  style={{height:'50vh'}}
                  />
                </Col>
            </Row>
        </Container>
        </div>
        <Container>
            <div className={cx('deli-location')}>
                <h1 style={{fontSize:35,fontWeight:'bold',color:'red'}}>CÁC KHU VỰC NỘI - NGOẠI THÀNH</h1>
                <h4>CỦA TP. HỒ CHÍ MINH</h4>
                <div className={cx('deli-location-section')}>
                  <h1 className={cx('deli-location-text')}>TP. Hồ Chí Minh</h1>
                  <h5>• Nội thành TP. Hồ Chí Minh:</h5>
                  <p>Quận 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, Thủ Đức, Tân Phú, Tân Bình, Phú Nhuận, Gò Vấp, Bình Thạnh, Bình Tân.</p>
                  <h5>• Ngoại thành TP. Hồ Chí Minh:</h5>
                  <p>Huyện Củ Chi, Huyện Hóc Môn, Huyện Bình Chánh, Huyện Nhà Bè.</p>
                </div>
            </div>
            <Row>
              <Col lg={12} className={cx('deli-in')}>
                <div className={cx('deli-in-content')}>
                  <h1 style={{color:'white'}}>Giao Hàng Nội Thành</h1>
                  <img  src='https://w.ladicdn.com/s450x400/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-28-20210129090434.png'
                    style={{height:'100%'}}
                  />
                </div>
                  <div className={cx('deli-in-img')}>
                      <img src='https://w.ladicdn.com/s750x600/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-08-20210128085147.png ' />
                  </div>
                  <h1 style={{fontSize:35,fontWeight:'bold',color:'red'}}>Miễn phí vận chuyển cho các đơn hàng nội thành  </h1>
                <h4>CỦA TP. HỒ CHÍ MINH</h4>
              </Col>
            </Row>
            <div className={cx('line')}></div>
            <Row>
              <Col lg={12} className={cx('deli-in')}>
                <div className={cx('deli-in-content')}>
                  <h1 style={{color:'white'}}>Giao Hàng Ngoại Thành</h1>
                  <img  src='https://w.ladicdn.com/s450x400/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-28-20210129090434.png'
                    style={{height:'100%'}}
                  />
                </div>
                  <div className={cx('deli-in-img')}>
                      <img src='https://w.ladicdn.com/s750x600/5bf3dc7edc60303c34e4991f/chinh-sach-giao-hang-08-20210128085147.png ' />
                  </div>
                  <h1 style={{fontSize:35,fontWeight:'bold',color:'red'}}>Miễn phí vận chuyển cho các đơn hàng ngoại thành  </h1>
                <h4>CỦA TP. HỒ CHÍ MINH</h4>
              </Col>
            </Row>
        </Container>
       
        <Footer />
    </div>

  )
}

export default guidedeli
