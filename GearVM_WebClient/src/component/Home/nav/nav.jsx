import React, { useState } from "react";
import styles from "./nav.modulo.scss";
import classNames from "classnames/bind";
import { MenuOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { Row, Col } from "react-bootstrap";
import NavModal from "../../Modal/NavModal";

const cx = classNames.bind(styles);
const Nav = () => {
  const navigate = useNavigate();

  const [open, setOpen] = useState(false);
  const PageGuidePayment = () => {
    navigate("/guidePayment", { replace: true });
  };
  const PageGuidePolicy = () => {
    navigate("/guidePolicy", { replace: true });
  };
  const PageGuideDeli = () => {
    navigate("/guideDeli", { replace: true });
  };
  const handleOpenCatalogy = () => {
    setOpen(!open);
  };

  return (
    <div className="navwrap">
      <Row>
        <Col sm={3}>
          <div className={cx("ListProduct")} onClick={handleOpenCatalogy}>
            <MenuOutlined />
            <h4 className={cx("textItemProduct")}>Danh Mục Sản Phẩm</h4>
          </div>
        </Col>
        <Col sm={3}>
          <div className={cx("itemProduct")} onClick={PageGuidePayment}>
            <MenuOutlined />

            <h4 className={cx("textItemProduct")}>Hướng dẫn Thanh Toán</h4>
          </div>
        </Col>
        <Col sm={3}>
          <div className={cx("itemProduct")} onClick={PageGuidePolicy}>
            <MenuOutlined />

            <h4 className={cx("textItemProduct")}>Chính sách bảo hành</h4>
          </div>
        </Col>
        <Col sm={3}>
          <div className={cx("itemProduct")} onClick={PageGuideDeli}>
            <MenuOutlined />

            <h4 className={cx("textItemProduct")}>Chính sách vận chuyển</h4>
          </div>
        </Col>
      </Row>
      <NavModal data={open}></NavModal>
    </div>
  );
};

export default Nav;
