import React from 'react';
import styles from './GuidePayment.modulo.scss'
import classNames from 'classnames/bind'
import Header from '../../component/Home/header/Header';
import Footer from '../../component/Home/footer/Footer';
const cx= classNames.bind(styles);
const GuidePayment = () => {
  return (
    <div>
      <Header/>
      <div id='SECTION2' className={cx('ladi-section')}>
      <div className={cx('ladi-container')}>
          <div id='BOX24' className={cx('ladi-element')}>
              <div  className={cx('ladi-box')}>
              </div>
          </div>
          <div className={cx('ladi-content-off')}>
                      <div className={cx('ladi-element-off')}>
                        <h5 className={cx('ladi-text')}>Thanh Toán Trực Tiếp</h5>
                        <img src='https://res.cloudinary.com/dowl1dqqy/image/upload/v1677495316/rspuwzct7hdemhzxryhg.png'
                          className={cx('ladi-img')}
                        />
                      </div>
          <div className={cx('thumb-off')}></div>
                      <div className={cx('ladi-content-text')}>
                        Quý khách có thể đến trực tiếp Showroom GEARVM để thanh toán.
                      </div>
          </div>
          <div className={cx('ladi-content-onl')}>
                      <div className={cx('ladi-element-off')}>
                        <h5 className={cx('ladi-text')}>Thanh Toán Trực Tuyến</h5>
                        <img src='https://res.cloudinary.com/dowl1dqqy/image/upload/v1677496786/Screenshot_2023-02-27_181848-removebg-preview_tghbgg.png'
                          className={cx('ladi-img')}
                        />
                      </div>
          <div className={cx('thumb-onl')}></div>
                      <div className={cx('ladi-content-text')}>
                        Quý khách có thể chuyển khoảng qua tài khoản của GEAMVM.
                      </div>
          </div>
          <div className={cx('ladi-content-deli')}>
                      <div className={cx('ladi-element-off')}>
                        <h5 className={cx('ladi-text')}>Thanh Toán Khi Nhận Hàng</h5>
                        <img src='https://res.cloudinary.com/dowl1dqqy/image/upload/v1677496762/Screenshot_2023-02-27_181834-removebg-preview_st6x7x.png'
                          className={cx('ladi-img')}
                        />
                      </div>
          <div className={cx('thumb-deli')}></div>
                      <div className={cx('ladi-content-text')}>
                        Quý khách có thể chuyển khoảng qua tài khoản của GEAMVM.
                      </div>
          </div>
      </div>
      <div className={cx('pay-onl')}>
          <div className={cx('pay-content')}>
            <div className={cx('pay-content-title')}>
                <img src='https://tse1.explicit.bing.net/th?id=OIP.xjowT4RN_p0Qo_4azjsfMgHaHa&pid=Api&P=0'
                  style={{width:80,height:50}}
                />
                <div className={cx('pay-content-title-thumb')}>
                  <h4 className={cx('pay-content-title-txtA')}>Momo digital wallet</h4>
                  <h6 className={cx('pay-content-title-txtB')}>Thanh Toán qua Ví MoMo</h6>
                </div>
            </div>
            <div className={cx('pay-content-step')}>
              <h4 className={cx('pay-content-step-txtA')}>Bước 1</h4>
              <h6>Sau khi chọn hình thức thanh toán qua ứng dụng MOMO, hệ thống sẽ chuyển sang 
                giao diện thanh toán của MOMO.
              </h6>
              <h4 className={cx('pay-content-step-txtA')}>Bước 2</h4>
              <h6>Mở ứng dụng Ví MOMO của bạn quét mã QR được cho để hoàn tất việc đặt hàng.</h6>
            </div>
          </div>
          <img src='https://websitecuatui.net/wp-content/uploads/2020/02/vi-momo-1013x570.jpg'
                  style={{width:'30vh',height:'50%'}}
                />
      </div>
      <div className={cx('thumb')}></div>
      <div className={cx('pay-deli')}>
        <div className={cx('paydeli-content')}>
            <h5  className={cx('pay-content-title-txtA')}>Payment on delivery</h5>
            <h5 className={cx('pay-content-title-txtB')}>Thanh toán khi nhận hàng</h5>
            <p className={cx('paydeli-content-txt')}>
              Khi quý khách hàng nhận hàng sẽ thanh toán tổng giá trị đơn hàng .
            </p>
            <p className={cx('paydeli-content-txt')}>Quý khách sẽ thanh toán tại địa điểm nhận hàng cho nhân viên 
             giao nhận của GearVN hoặc đơn vị vận chuyển mà GearVN sử dụng.
            </p>
        </div>
        
          <img src='https://w.ladicdn.com/s850x700/5bf3dc7edc60303c34e4991f/thanh-toan-25-20210809040641.png' 
          style={{width:'30vh',height:'50%'}}
          />
       
      </div>
      <div className={cx('thumb')}></div>
      <div className={cx('pay-deli')}>
        <div className={cx('paydeli-content')}>
            <h5  className={cx('pay-content-title-txtA')}>Direct payments</h5>
            <h5 className={cx('pay-content-title-txtB')}>Thanh toán trực tiếp</h5>
            <p className={cx('paydeli-content-txt')}>
            Quý khách hàng có thể đến trực tiếp Showroom GEARVN  để thanh toán bằng tiền mặt .
            </p>
        </div>
        
          <img src='https://admin.binhminhdigital.com/StoreData/images/PageData/Thumbnail%20thanh%20toan%20truc%20tiep.jpg' 
          style={{width:'30vh',height:'50%'}}
          />
       
      </div>
      </div>
      <div className={cx('footer')}>  
        <Footer />
      </div>
    </div>
  )
}

export default GuidePayment
