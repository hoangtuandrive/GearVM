import React from 'react'
import styles from'./Footer.modulo.scss';
import classNames from 'classnames/bind';

const cx=classNames.bind(styles);
const Footer = () => {
  return (
    <div className={cx('wrapFooter')}>
    <div className={cx('wrapFooter_about')}>
        <div className={cx('footer_left')}>
            <h3>CÔNG TY CỔ PHẦN THƯƠNG MẠI - DỊCH VỤ GEARVM</h3>
            <h6>&copy;2022 - 2023 Công Ty Cổ Phần Thương Mại - Dịch Vụ Gearvm</h6>
            <h6>Giấy chứng nhận đăng ký doanh nghiệp: 0394758354 do Sở KH-ĐT TP.HCM cấp lần đầu ngày 26 tháng 12 năm 2022</h6>
        </div>
        <div className={cx('footer_center')}>
            <div className={cx('footer_center_content')}>
                <h5>Địa chỉ trụ sở chính:</h5>
                <p>Số 12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, Thành Phố Hồ Chí Minh</p>
            </div>
            <div className={cx('footer_center_content')}>
                <h5>Văn phòng điều hành:</h5>
                <p>Số 12 Hà Huy Giáp, Phường Thạnh Lộc, Quận 12, Thành Phố Hồ Chí Minh</p>
            </div>
        </div>
      
            <img 
                src={require('../../../assets/moc.jpg')}
                className={cx('footer_right_img')}
            />
       
    </div>
    </div> 
  )
}

export default Footer
