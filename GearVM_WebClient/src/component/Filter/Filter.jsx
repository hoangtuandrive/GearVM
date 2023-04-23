import React from "react";
import styles from "./Filter.modulo.scss";
import classNames from "classnames/bind";
import { useNavigate } from "react-router-dom";

const cx = classNames.bind(styles);

const Filter = ({ data, dropdown }) => {
  const navigate = useNavigate();
  const handleChangePage = (item) => {
    navigate(`/catalog?type=${data.name}&brand=${item}`, {
      replace: true,
    });
  };
  const handleChangePagePrice = (price) => {
    // navigate(`/catalog?type=${data.name}&brand=${item}`, {
    //   replace: true,
    // });

    const priceStrA = parseInt(price.replace(/\D/g, ""));

    if (priceStrA >= 100) {
      const priceStrMin = parseInt(
        price
          .split("-")[0]
          .replace(/^[^0-9]+/, "")
          .replace(/\D/g, "")
      );
      const priceStrMax = parseInt(
        price
          .split("-")[1]
          .replace(/^[^0-9]+/, "")
          .replace(/\D/g, "")
      );
      navigate(
        `/catalog?type=${data.name}&min=${priceStrMin}&max=${priceStrMax}`,
        {
          replace: true,
        }
      );
    } else {
      navigate(`/catalog?type=${data.name}&max=${priceStrA}`, {
        replace: true,
      });
    }
  };
  return (
    <div className={cx(`wrapFilter ${dropdown ? "show" : ""}`)}>
      {/* {console.log(data)} */}
      <div className={cx("trademark")}>
        <h2 className={cx("trademark_txt")}>{data.thuongHieu}</h2>
        {data.listTH &&
          data.listTH.map((th, index) => (
            <h4
              key={index}
              className={cx("trademark_listTxt")}
              onClick={() => handleChangePage(th)}
            >
              {th}
            </h4>
          ))}
      </div>
      <div className={cx("trademark")}>
        <h2 className={cx("trademark_txt")}>{data.cauHinh}</h2>
        {data.listCH.map((th, index) => (
          <h4 key={index} className={cx("trademark_listTxt")}>
            {th}
          </h4>
        ))}
      </div>
      <div className={cx("trademark")}>
        <h2 className={cx("trademark_txt")}>{data.Gia}</h2>
        {data.listGia.map((th, index) => (
          <h4
            key={index}
            className={cx("trademark_listTxt")}
            onClick={() => handleChangePagePrice(th)}
          >
            {th}
          </h4>
        ))}
      </div>
    </div>
  );
};

export default Filter;
