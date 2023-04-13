import React from "react";
import styles from "./BankingPage.module.scss";
import classNames from "classnames/bind";
import { Container, Row, Col } from "react-bootstrap";
import { Button } from "antd";
import {
  faCoins,
  faAddressCard,
  faUser,
  faArrowLeft,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const cx = classNames.bind(styles);

const BankingPage = () => {
  const navigate = useNavigate();
  const handleComplete = () => {
    navigate("/payment-success");
  };
  const handleBack = () => {
    navigate("/payOffline");
  };

  const cart = useSelector((state) => state.todoCart);

  return (
    <div className={cx("wrapMoMoPage")}>
      <Container className={cx("MomoPageContent")}>
        <Row>
          <Col lg={4} className={cx("MomoPageContentLeft")}>
            <div>
              <div>
                <h6 className={cx("Suptxt")}>Nhà cung cấp</h6>
                <h4 className={cx("NameCty")}>
                  CÔNG TY THƯƠNG MẠI DỊCH VỤ GEARVM
                </h4>
              </div>
              <hr style={{ color: "white" }} />
              <div>
                <div className={cx("MomoTotal")}>
                  <FontAwesomeIcon icon={faCoins} style={{ color: "white" }} />
                  <h6 className={cx("Totaltxt")}>Số tiền</h6>
                </div>
                <h4 className={cx("TotaltxtMagin")}>
                  {" "}
                  {new Intl.NumberFormat("de-DE", {
                    style: "currency",
                    currency: "VND",
                  }).format(cart.cartTotalAmount)}
                </h4>
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
                <h4 className={cx("TotaltxtMagin")}>643704060075146</h4>
              </div>
              <hr style={{ color: "white" }} />
              <div>
                <div className={cx("MomoTotal")}>
                  <FontAwesomeIcon icon={faUser} style={{ color: "white" }} />
                  <h6 className={cx("Totaltxt")}>Tên Tài Khoản</h6>
                </div>
                <h4 className={cx("TotaltxtMagin")}>Giang Vũ Hoàng Tuấn</h4>
              </div>
            </div>
            <div className={cx("btnBack")} onClick={handleBack}>
              <FontAwesomeIcon
                icon={faArrowLeft}
                style={{ color: "white", marginLeft: 5, fontWeight: "bold" }}
              />
              <h6 className={cx("btnBackTxt")}>Quay lại</h6>
            </div>
          </Col>
          <Col lg={8} className={cx("wrapRight")}>
            <div className={cx("MomoPageContentRight")}>
              <img
                src={require("../../assets/logoGear.jpg")}
                className={cx("imgLogo")}
              />
              <img
                src={require("../../assets/LogoVib.jpg")}
                className={cx("imgLogo")}
              />
            </div>
            <hr />
            <div className={cx("ScanCotent")}>
              <div className={cx("Scantxt")}>Quét Mã Để Thanh Toán</div>
              <div className={cx("QrCode")}>
                <img
                  src={require("../../assets/QrVib.jpg")}
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
