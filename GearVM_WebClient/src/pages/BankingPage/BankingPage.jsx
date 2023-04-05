import React from "react";
import styles from "./BankingPage.module.scss";
import classNames from "classnames/bind";
import { Container, Row, Col } from "react-bootstrap";
import { Button } from "antd";
import {
  faCoins,
  faAddressCard,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
const cx = classNames.bind(styles);

const BankingPage = () => {
  const navigate = useNavigate();
  const handleComplete = () => {
    navigate("/payment-success");
  };
  return (
    <div className={cx("wrapMoMoPage")}>
      <Container className={cx("MomoPageContent")}>
        <Row>
          <Col lg={4} className={cx("MomoPageContentLeft")}>
            <div>
              <h6 className={cx("Suptxt")}>Nhà cung cấp</h6>
              <h4 className={cx("NameCty")}>CÔNG TY THIẾT BỊ ĐIỆN TỬ GEARVM</h4>
            </div>
            <hr style={{ color: "white" }} />
            <div>
              <div className={cx("MomoTotal")}>
                <FontAwesomeIcon icon={faCoins} style={{ color: "white" }} />
                <h6 className={cx("Totaltxt")}>Số tiền</h6>
              </div>
              <h4 className={cx("TotaltxtMagin")}>10.00đ</h4>
            </div>
            <hr style={{ color: "white" }} />
            <div>
              <div className={cx("MomoTotal")}>
                <FontAwesomeIcon
                  icon={faAddressCard}
                  style={{ color: "white" }}
                />
                <h6 className={cx("Totaltxt")}>Số Tài Khoản</h6>
              </div>
              <h4 className={cx("TotaltxtMagin")}>9148407</h4>
            </div>
            <hr style={{ color: "white" }} />
            <div>
              <div className={cx("MomoTotal")}>
                <FontAwesomeIcon icon={faUser} style={{ color: "white" }} />
                <h6 className={cx("Totaltxt")}>Tên Tài Khoản</h6>
              </div>
              <h4 className={cx("TotaltxtMagin")}>Trần Hoàng Long</h4>
            </div>
          </Col>
          <Col lg={8} className={cx("wrapRight")}>
            <div className={cx("MomoPageContentRight")}>
              <img
                src={require("../../assets/logoGear.jpg")}
                className={cx("imgLogo")}
              />
              <img
                src={require("../../assets/acbbank.jpg")}
                className={cx("imgLogo")}
              />
            </div>
            <hr />
            <div className={cx("ScanCotent")}>
              <div className={cx("Scantxt")}>Quét Mã Để Thanh Toán</div>
              <div className={cx("QrCode")}>
                <img
                  src={require("../../assets/QrCode.png")}
                  className={cx("imgQr")}
                />
              </div>
              <div className={cx("Intro")}>
                <h6 className={cx("Introtxt")}>
                  Sử dụng App ngân hàng hoặc ứng dụng Camera hỗ trợ QR code để
                  quét mã
                </h6>
              </div>
              <div className={cx("Momobtn")}>
                <button className={cx("btnComplete")} onClick={handleComplete}>
                  {" "}
                  Hoàn Thành Thanh Toán
                </button>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default BankingPage;
