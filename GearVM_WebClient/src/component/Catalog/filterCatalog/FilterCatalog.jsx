import React, { useContext } from "react";
import styles from "./FilterCatalog.modulo.scss";
import classNames from "classnames/bind";
import { faFilter } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { AppContext } from "../../context/AppProvider";
import OffcanvasCatalog from "../offcanvasCatalog/OffcanvasCatalog";

const cx = classNames.bind(styles);

const FilterCatalog = () => {
  document.addEventListener("scroll", () => {
    const top = document.documentElement.scrollTop;
    if (top != 0) {
      document.querySelector(".mobileFilter")?.classList.add("sticky");
      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".mobileFilter")?.classList.remove("sticky");
    }
  });

  const { setShowFilter } = useContext(AppContext);
  const handlerShow = () => {
    setShowFilter(true);
  };
  return (
    <div className={cx("mobileFilter")}>
      <FontAwesomeIcon
        icon={faFilter}
        style={{ fontSize: 18 }}
        onClick={handlerShow}
      />
      <h5 className={cx("mobileFiltertxt")}>Bộ lọc</h5>
      <OffcanvasCatalog />
    </div>
  );
};

export default FilterCatalog;
