import React from "react";
import styles from "./Filter.modulo.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);

const Filter = ({ data }) => {
  console.log(data);
  return (
    <div className={cx("wrapFilter")}>
      <div className={cx("trademark")}>
        <h2 className={cx("trademark_txt")}>{data.thuongHieu}</h2>
        {data.listTH.map((th, index) => (
          <h4 key={index} className={cx("trademark_listTxt")}>
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
          <h4 key={index} className={cx("trademark_listTxt")}>
            {th}
          </h4>
        ))}
      </div>
    </div>
  );
};

export default Filter;
