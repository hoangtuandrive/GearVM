import React, { useContext } from "react";
import Offcanvas from "react-bootstrap/Offcanvas";
import Dropdown from "react-bootstrap/Dropdown";
import { AppContext } from "../context/AppProvider";
import { LaptopOutlined, UserOutlined } from "@ant-design/icons";
import styles from "./OffcanvasMenu.module.scss";
import classNames from "classnames/bind";
import Accordion from "react-bootstrap/Accordion";
import ListGroup from "react-bootstrap/ListGroup";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import authSlice from "../../redux/slices/AuthSlices";
import Acount from "../Custom/Acount/Acount";
import dataNavModal from "../../dataUI/dataNavModal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import AccordionBody from "./AccordionBody";
const cx = classNames.bind(styles);
const OffcanvasMenu = () => {
  const { openMenu, setOpenMenu } = useContext(AppContext);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const auth = useSelector((state) => state.auth);

  const token = localStorage.getItem("token");
  // const [show, setShow] = useState(false);
  const handleClose = () => setOpenMenu(false);
  // const handleShow = () => setOpenMenu(true);
  // console.log(openMenu);
  const handleShow = () => {
    navigate("/login", { replace: true });
  };
  const handleSignOut = () => {
    dispatch(authSlice.actions.logoutUser(null));
  };
  const PageGuidePayment = () => {
    navigate("/guidePayment", { replace: true });
  };
  const PageGuidePolicy = () => {
    navigate("/guidePolicy", { replace: true });
  };
  const PageGuideDeli = () => {
    navigate("/guideDeli", { replace: true });
  };
  const PagePromotion = () => {
    navigate("/promotion", { replace: true });
  };

  const handleChangePageType = (item) => {
    navigate(`/catalog?filed=${item}`, {
      replace: true,
    });
    console.log(item);
  };


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
          {token ? (
            <div className={cx("iconAccess")}>
              <Acount />
            </div>
          ) : (
            <div className={cx("iconAccess")} onClick={handleShow}>
              <UserOutlined style={{ fontSize: 30 }} />
              <div className={cx("textAccess")}>
                <h5 className={cx("lblAccess")}>Đăng Nhập</h5>
                <h5 className={cx("lblAccess")}>Đăng Ký</h5>
              </div>
            </div>
          )}
          <div className={cx("offcanNav_header")}>Danh Mục</div>
          {dataNavModal.map((item, index) => (
            <Accordion defaultActiveKey={index} key={index}>
              <Accordion.Item eventKey="0">
                <Accordion.Header
                  onDoubleClick={() => handleChangePageType(item.name)}
                >
                  <FontAwesomeIcon icon={item.icon} />
                  {item.name}
                </Accordion.Header>
                <AccordionBody data={item} />
              </Accordion.Item>
            </Accordion>
          ))}

          <div className={cx("offcanNav_header")}>Thông Tin</div>
          <div className={cx("offcanNav")}>
            {token ? (
              <div className={cx("offcanNav_item")}>Thông tin đơn hàng</div>
            ) : null}
            <div onClick={PageGuidePayment} className={cx("offcanNav_item")}>
              Hướng dẫn thanh toán
            </div>
            <div onClick={PageGuidePolicy} className={cx("offcanNav_item")}>
              Chính Sách bảo hành
            </div>
            <div onClick={PageGuideDeli} className={cx("offcanNav_item")}>
              Chính Sách vận chuyển
            </div>
            <div onClick={PagePromotion} className={cx("offcanNav_item")}>
              Khuyến mãi
            </div>
          </div>
          {token ? (
            <input
              type="button"
              className={cx("wrapMenuAcount_thumb_btn")}
              value="Đăng xuất"
              onClick={handleSignOut}
            />
          ) : null}
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  );
};

export default OffcanvasMenu;
