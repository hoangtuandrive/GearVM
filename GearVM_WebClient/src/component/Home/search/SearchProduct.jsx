import { useState } from "react";
import classNames from "classnames/bind";
import styles from "./SearchProduct.module.scss";
import { Input } from "antd";

import dataSearch from "../../../dataUI/dataSearch";

const cx = classNames.bind(styles);
export default function SearchProduct() {
  const { Search } = Input;
  const [value, setValue] = useState("");
  const searchTerm = value.toLowerCase();
  const onChange = (event) => {
    setValue(event.target.value);
  };

  const onSearch = (searchTerm) => {
    setValue(searchTerm);
    // our api to fetch the search result
    console.log("search ", searchTerm);
  };

  return (
    <div className="wrapSearch">
      {/* <h1>Search</h1> */}

      <div className={cx("search-container")}>
        <div className={cx("search-inner")}>
          {/* <input type="text" value={value} onChange={onChange} /> */}
          {/* <button onClick={() => onSearch(value)}> Search </button> */}
          <Search
            placeholder="input search text"
            enterButton
            value={value}
            onChange={onChange}
            onClick={() => onSearch(value)}
          />
        </div>
        <div className={cx("dropdown")}>
          {dataSearch
            .map((item) => {
              const name = item.name.toLowerCase();
              const branch = item.branch.toLowerCase();

              if (
                searchTerm &&
                name.startsWith(searchTerm) &&
                name !== searchTerm
              ) {
                return item.name;
              } else if (
                searchTerm &&
                branch.startsWith(searchTerm) &&
                branch !== searchTerm
              ) {
                return item.branch;
              }
            })
            .slice(0, 10)
            .map((item, index) => (
              <div
                onClick={() => onSearch(item)}
                className={cx(item ? "dropdown-row" : null)}
                key={index}
              >
                {item}
              </div>
            ))}
        </div>
      </div>
    </div>
  );
}
