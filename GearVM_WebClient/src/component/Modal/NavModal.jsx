import React, { useState } from "react";
import styles from "./NavModal.modulo.scss";
import classNames from "classnames/bind";
import { LaptopOutlined } from "@ant-design/icons";
import Filter from "../Filter/Filter";
import { useEffect } from "react";
import Tippy from "@tippyjs/react/headless";
import dataNavModal from "../../dataUI/dataNavModal";
import { useNavigate } from "react-router-dom";

const cx = classNames.bind(styles);
// const $=document.querySelector.bind(document)
const NavModal = ({ data }) => {
  const [dropdown, setDropdown] = useState(false);
  const [fil, setFil] = useState({
    id: 3,
    name: "PC special",
    thuongHieu: "Lọc theo thương hiệu",
    listTH: ["Acer", "Asus", "HP", "Del", "MSI"],
    cauHinh: "Lọc theo cấu hình",
    listCH: [
      "Intel Core i8",
      "Intel Core i7",
      "Intel Core i6",
      "AMD Ryzen 3",
      "AMD Ryzen 6",
    ],
    Gia: "Lọc theo giá",
    listGia: [
      "Dưới 10 triệu",
      "10-15 triệu",
      "15-20 triệu",
      "20-25 triệu",
      "25-30 triệu",
      "Trên 40 triệu",
    ],
  });
  const navigate = useNavigate();
  const $$ = document.querySelectorAll.bind(document);
  const navContent = $$(".NavContent_item");
  //   console.log(navContent);
  const top = document.documentElement.scrollTop;
  const [openFilter, setopenFilter] = useState(false);
  document.addEventListener("scroll", () => {
    const top = document.documentElement.scrollTop;
    if (top != 0) {
      document.querySelector(".wrapNavModal").classList.add("sticky");
      // document.querySelector('.wrapnav_about').setAttribute('class','sticky')
    } else {
      document.querySelector(".wrapNavModal").classList.remove("sticky");
    }
  });

  navContent.forEach((nav, index) => {
    nav.onclick = () => {
      setopenFilter(!openFilter);
    };
  });
  useEffect(() => {
    if (data === true) {
      document.querySelector(".wrapNavModal").classList.add("active");
    } else {
      document.querySelector(".wrapNavModal").classList.remove("active");
    }
  }, [!data]);
  const handleFilter = (item) => {
    setFil(item);
    setDropdown((prev) => !prev);
    navigate("/catalog", { replace: true });
  };
  return (
    <div className={cx("wrapNavModal ")}>
      <div className={cx("NavContent")}>
        {dataNavModal.map((item, index) => (
          <div
            className={cx("NavContent_item")}
            key={item.id}
            aria-expanded={dropdown ? "true" : "false"}
            onClick={() => handleFilter(item)}
          >
            {/* {console.log(fil)} */}
            <LaptopOutlined style={{ fontSize: 20 }} />
            <span style={{ fontSize: 18, marginLeft: 15 }}>{item.name}</span>
          </div>
          // </Tippy>
        ))}
      </div>
      <Filter data={fil} dropdown={dropdown} />
    </div>
  );
};

export default NavModal;
