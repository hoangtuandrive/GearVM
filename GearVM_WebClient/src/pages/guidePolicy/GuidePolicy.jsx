import React from 'react';
import styles from './GuidePolicy.modulo.scss'
import classNames from 'classnames/bind'
import Header from '../../component/Home/header/Header';
import Footer from '../../component/Home/footer/Footer';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const cx= classNames.bind(styles);

const GuidePolicy = () => {
  return (
    <div>
        <Header />
    <Container >
      
        <Row className={cx('Policy-img')}>
            <Col lg={8} >
                <h3 className={cx('Policy-content-txt')}>CHÍNH SÁCH BẢO HÀNH</h3>
                <h4 className={cx('Policy-content-txtGear')}>TẠI GEARVM</h4>
                <p className={cx('Policy-content-txtTitle')}>
                Nếu Quý Khách gặp khó khăn trong việc liên hệ Trung Tâm Bảo Hành, Quý Khách vui lòng liên hệ với bộ phận chăm sóc khách hàng của GearVN theo thông tin sau để được hỗ trợ:
                </p>    
                <p className={cx('Policy-content-txtHoline')}>• Hotline: 1800.6173</p>
            </Col>
            <Col lg={4}>
                <img src='https://res.cloudinary.com/dowl1dqqy/image/upload/v1677672248/chinh-sach-bao-hanh-colorful-removebg-preview_nw4ohv.png'
                style={{width:'50vh',height:'90%'}}
                />
            </Col>
        </Row>
    
        <Row>
           <Col lg={12}>
                <h1 style={{color:'red',fontWeight:'bold'}}>I. CÁC BƯỚC BẢO HÀNH SẢN PHẨM</h1>
           </Col>
        </Row>
        <Row>
            <Col lg={8}>
                <div className={cx('Policy-step1')}>
                    <h5 style={{color:'red',fontWeight:'bold'}}>Bước 1</h5>
                    <h4 style={{fontSize:14}}>Khi bạn có nhu cầu bảo hành sản phẩm,khách hàng vui lòng liên hệ với GearVM qua các hình thức sau:</h4>
                    <h6>• Tổng đài bảo hành: 1800.6173</h6>
                    <h6>• Liên hệ trực tiếp tại cửa hàng chi nhánh GEARVM</h6>
                </div>
            </Col>
            <Col lg={4}>
                <img src='https://w.ladicdn.com/s550x500/5bf3dc7edc60303c34e4991f/chinhsachbaohanh-recovered-09-20201021093523.png' 
                style={{height:'30vh'}}
                />
            </Col>
        </Row>
        <Row>
            <Col lg={8}>
                <div className={cx('Policy-step1')}>
                    <h5 style={{color:'red',fontWeight:'bold'}}>Bước 2</h5>
                    <h4 style={{fontSize:14}}>Quý khách cần mang hàng đến cửa hàng GEARVM để được bảo hành</h4>
                </div>
            </Col>
            <Col lg={4}>
                <img src='https://w.ladicdn.com/s550x500/5bf3dc7edc60303c34e4991f/chinhsachbaohanh-recovered-10-20201021093523.png' 
                style={{height:'30vh'}}
                />
            </Col>
        </Row>
        <Row>
            <Col lg={8}>
                <div className={cx('Policy-step1')}>
                    <h5 style={{color:'green',fontWeight:'bold'}}>Bước 3</h5>
                    <h4 style={{fontSize:14,color:'green'}}>Hoàn tất xử lý đơn hàng và giao hàng cho khách hàng</h4>
                </div>
            </Col>
            <Col lg={4}>
                <img src='https://w.ladicdn.com/s550x500/5bf3dc7edc60303c34e4991f/chinhsachbaohanh-recovered-11-20201021093523.png' 
                style={{height:'30vh'}}
                />
            </Col>
        </Row>
        <Row>
            <Col lg={12}>
            <h1 style={{color:'red',fontWeight:'bold'}}>II. ĐIỀU KIỆN BẢO HÀNH SẢN PHẨM</h1>
            </Col>
        </Row>
        <Row>
            <Col lg={3}>
                <h4 style={{fontSize:27,fontWeight:'700'}}>NHỮNG SẢN PHẨM ĐỦ ĐIỀU KIỆN BẢO HÀNH</h4>
                <div className={cx('line')}></div>
            </Col>
            <Col lg={9}>
                <h6>• Sản phẩm nếu có tem niêm phong (seal) trên sản phẩm thì tem niêm phong phải còn nguyên vẹn.</h6>
                <h6>• Đối với sản phẩm bảo hành trên hộp: sản phẩm còn đầy đủ hộp.</h6>
                <h6>• Sản phẩm không trầy xước, cấn móp, bể, vỡ, biến dạng so với ban đầu.</h6>
            </Col>
        </Row>
        <Row className={cx('policy-noncondition')}>
            <Col lg={3}>
                <h4 style={{fontSize:27,fontWeight:'700'}}>NHỮNG SẢN PHẨM KHÔNG ĐỦ ĐIỀU KIỆN BẢO HÀNH</h4>
                <div className={cx('line')}></div>
            </Col>
            <Col lg={9}>
                <h6>• Hết thời hạn bảo hành.</h6>
                <h6>• Không có tem niêm phong, hoặc bị tẩy xóa, không còn nguyên dạng ban đầu.</h6>
                <h6>• Bị tác động vật lý làm trầy xước, cong vênh, rạn nứt, bể vỡ trong quá trình quá trình sử dụng.</h6>
                <h6>• Bị hư hỏng do tự ý thảo mở, sửa chữa, thay đổi cấu trúc sản phẩm bên trong mà chưa có sự xác nhận đồng ý hoặc giám sát bởi kỹ thuật viên GearVN.</h6>
                <h6>• Bị hư hỏng, chập, cháy do sử dụng sai mục đích, tự ý tháo, lắp đặt không tuân theo các hướng dẫn sử dụng đính kèm theo sản phẩm.</h6>
                <h6>• Bị hư hỏng do côn trùng xâm nhập (chuột, gián, kiến, mối…)</h6>
                <h6>• Bị hư hỏng do thiên tai, hỏa hoạn, lụt lội, sét đánh, rỉ sét, hao mòn do môi trường gây ra.</h6>
            </Col>
        </Row>
    </Container>
    <Footer />
    </div>
   
  )
}

export default GuidePolicy
