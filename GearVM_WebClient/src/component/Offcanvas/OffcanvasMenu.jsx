import React, { useContext } from "react";
import Offcanvas from "react-bootstrap/Offcanvas";
import Dropdown from "react-bootstrap/Dropdown";
import { AppContext } from "../context/AppProvider";
import { LaptopOutlined, UserOutlined } from "@ant-design/icons";
import styles from "./OffcanvasMenu.module.scss";
import classNames from "classnames/bind";
import Accordion from "react-bootstrap/Accordion";
import ListGroup from "react-bootstrap/ListGroup";
import Nav from "../Home/nav/Nav";
import Acount from "../Custom/Acount/Acount";
const cx = classNames.bind(styles);
const OffcanvasMenu = () => {
  const { openMenu, setOpenMenu } = useContext(AppContext);
  // const [show, setShow] = useState(false);
  const handleClose = () => setOpenMenu(false);
  // const handleShow = () => setOpenMenu(true);
  console.log(openMenu);
  return (
    <div>
      {/* <Button variant="primary" onClick={handleShow}>
        Launch
      </Button> */}
      <Offcanvas
        show={openMenu}
        onHide={handleClose}
        scroll="true"
        className={cx("offcanva")}
      >
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>GEARVM</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>
          <div className={cx("iconAccess")}>
            <UserOutlined style={{ fontSize: 30 }} />
            <div className={cx("textAccess")}>
              <h5 className={cx("lblAccess")}>Đăng Nhập</h5>
              <h5 className={cx("lblAccess")}>Đăng Ký</h5>
            </div>
            {/* <Acount /> */}
          </div>
          <div className={cx("offcanNav_header")}>Danh Mục</div>
          <Accordion defaultActiveKey="0">
            <Accordion.Item eventKey="0">
              <Accordion.Header>
                <LaptopOutlined />
                Laptop
              </Accordion.Header>
              <Accordion.Body>
                <Accordion defaultActiveKey="1">
                  <Accordion.Item eventKey="11">
                    <Accordion.Header>Thương Hiệu</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>Asus</ListGroup.Item>
                        <ListGroup.Item>Acer</ListGroup.Item>
                        <ListGroup.Item>MSI </ListGroup.Item>
                        <ListGroup.Item>HP</ListGroup.Item>
                        <ListGroup.Item>DELL</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                  <Accordion.Item eventKey="12">
                    <Accordion.Header>Theo giá bán</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>10Triệu-15Triệu</ListGroup.Item>
                        <ListGroup.Item>15Triệu-20Triệu</ListGroup.Item>
                        <ListGroup.Item>20Triệu-25Triệu </ListGroup.Item>
                        <ListGroup.Item>25Triệu-30Triệu</ListGroup.Item>
                        <ListGroup.Item>30Triệu trở lên</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                  <Accordion.Item eventKey="13">
                    <Accordion.Header>Theo nhu cầu</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>LapTop đồ họa</ListGroup.Item>
                        <ListGroup.Item>
                          Laptop học sinh sinh viên
                        </ListGroup.Item>
                        <ListGroup.Item>Laptop mỏng nhẹ</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                </Accordion>
              </Accordion.Body>
            </Accordion.Item>
            <Accordion.Item eventKey="2">
              <Accordion.Header>
                <LaptopOutlined />
                Bàn Phím
              </Accordion.Header>
              <Accordion.Body>
                <Accordion defaultActiveKey="2">
                  <Accordion.Item eventKey="22">
                    <Accordion.Header>Thương Hiệu</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>Asus</ListGroup.Item>
                        <ListGroup.Item>Acer</ListGroup.Item>
                        <ListGroup.Item>MSI </ListGroup.Item>
                        <ListGroup.Item>HP</ListGroup.Item>
                        <ListGroup.Item>DELL</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                  <Accordion.Item eventKey="23">
                    <Accordion.Header>Theo giá bán</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>10Triệu-15Triệu</ListGroup.Item>
                        <ListGroup.Item>15Triệu-20Triệu</ListGroup.Item>
                        <ListGroup.Item>20Triệu-25Triệu </ListGroup.Item>
                        <ListGroup.Item>25Triệu-30Triệu</ListGroup.Item>
                        <ListGroup.Item>30Triệu trở lên</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                  <Accordion.Item eventKey="24">
                    <Accordion.Header>Theo nhu cầu</Accordion.Header>
                    <Accordion.Body>
                      <ListGroup>
                        <ListGroup.Item>LapTop đồ họa</ListGroup.Item>
                        <ListGroup.Item>
                          Laptop học sinh sinh viên
                        </ListGroup.Item>
                        <ListGroup.Item>Laptop mỏng nhẹ</ListGroup.Item>
                      </ListGroup>
                    </Accordion.Body>
                  </Accordion.Item>
                </Accordion>
              </Accordion.Body>
            </Accordion.Item>
          </Accordion>
          <div className={cx("offcanNav_header")}>Thông Tin</div>
          <div className={cx("offcanNav")}>
            <div>Thông tin đơn hàng</div>
            <div>Hướng dẫn thanh toán</div>
            <div> Chính Sách bảo hành </div>
            <div> Chính Sách vận chuyển </div>
            <div> Khuyến mãi </div>
          </div>
          <input
            type="button"
            className={cx("wrapMenuAcount_thumb_btn")}
            value="Đăng xuất"
          />
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  );
};

export default OffcanvasMenu;
