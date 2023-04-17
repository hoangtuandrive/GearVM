import React, { useContext, useState } from "react";
import Offcanvas from "react-bootstrap/Offcanvas";
import { AppContext } from "../../context/AppProvider";
import styles from "./OffcannvasCatalog.module.scss";
import classNames from "classnames/bind";
import { Button, Slider, Tooltip } from "antd";

const cx = classNames.bind(styles);

const OffcanvasCatalog = () => {
  const { showFilter, setShowFilter } = useContext(AppContext);
  const [minValue, set_minValue] = useState(0);
  const [maxValue, set_maxValue] = useState(100);
  const handleCloser = () => {
    setShowFilter(false);
  };
  const handleChangePrice = (e) => {
    set_minValue(e[0]);
    set_maxValue(e[1]);
  };

  return (
    <Offcanvas
      show={showFilter}
      onHide={handleCloser}
      placement="end"
      className={cx("offcanva")}
    >
      <Offcanvas.Header closeButton></Offcanvas.Header>
      <Offcanvas.Body>
        <div className={cx("wrapFilterCatalog")}>
          <div className={cx("wrapCatalogProduct_fillter")}>
            <div className={cx("wrapCatalogProduct_fillter_Price")}>
              <h6 className={cx("txtWrap_Head")}>Lọc Theo giá tiền</h6>
              <div className={cx("bodyPrice")}>
                <p className={cx("bodyPrice_content")}>
                  {" "}
                  {new Intl.NumberFormat("de-DE", {
                    style: "currency",
                    currency: "VND",
                  }).format(minValue * 1000000)}
                </p>
                <div className={cx("bodyPrice_content")}>
                  <p>
                    {" "}
                    {new Intl.NumberFormat("de-DE", {
                      style: "currency",
                      currency: "VND",
                    }).format(maxValue * 1000000)}
                  </p>
                </div>
              </div>
              <Slider
                autoAdjustOverflow={true}
                tooltip={{ open: false }}
                range
                defaultValue={[0, 100]}
                onChange={handleChangePrice}
                className={cx("changePrice")}
              />
            </div>
          </div>
          <div className={cx("btnOff")}>
            <Button>Áp dụng</Button>
            <Button>Xóa bộ lọc</Button>
          </div>
        </div>
      </Offcanvas.Body>
    </Offcanvas>
  );
};

export default OffcanvasCatalog;
