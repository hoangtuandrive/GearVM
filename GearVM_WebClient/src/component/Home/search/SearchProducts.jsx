import React, { useEffect, useState } from "react";
import { Form, Modal, Select, Spin, Avatar, Button } from "antd";
import { debounce } from "lodash";
import axios from "axios";
import { url } from "../../../API/api";
import classNames from "classnames/bind";
import styles from "./SearchProduct.module.scss";
import { useNavigate } from "react-router-dom";
import { SearchOutlined } from "@ant-design/icons";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
const cx = classNames.bind(styles);

function DebounceSelect({ fetchOptions, debounceTimeout = 300, ...props }) {
  // Search: abcddassdfasdf

  const navigate = useNavigate();

  const [fetching, setFetching] = useState(false);
  const [options, setOptions] = useState([]);
  const [valueSearch, setValueSearch] = useState("");

  const debounceFetcher = React.useMemo(() => {
    const loadOptions = (value) => {
      setOptions([]);
      setFetching(true);
      setValueSearch(value);
      fetchOptions(value).then((newOptions) => {
        setOptions(newOptions);
        setFetching(false);
      });
    };

    return debounce(loadOptions, debounceTimeout);
  }, [debounceTimeout, fetchOptions]);

  React.useEffect(() => {
    return () => {
      // clear when unmount
      setOptions([]);
    };
  }, []);
  const [form] = Form.useForm();
  const handleSearch = () => {
    // console.log(valueSearch);
    // console.log(option);
    console.log(123);
    navigate(`/catalog?filed=${valueSearch}`, {
      replace: true,
    });
  };

  const handleSelect = (value, option) => {
    setOptions([]);
    navigate(`/productdetail?name=${option.name}&id=${option.key}`, {
      replace: true,
    });
  };
  return (
    <div className={cx("formSearch")}>
      <Select
        showSearch
        labelInValue
        filterOption={false}
        onSearch={debounceFetcher}
        notFoundContent={fetching ? <Spin size="small" /> : null}
        onSelect={handleSelect}
        {...props}
      >
        {options?.map((opt) => (
          <Select.Option
            key={opt.id}
            value={opt.id}
            title={opt.name}
            type={opt.type}
            name={opt.name}
            brand={opt.brand}
          >
            {` ${opt.name}`}
          </Select.Option>
        ))}
      </Select>
      <div className={cx("iconSearch")} onClick={handleSearch}>
        <FontAwesomeIcon icon={faSearch} />
      </div>
    </div>
  );
}

const fetchProduct = async (search) => {
  console.log(search);
  const productUrl = `${url}/products/filter-search?pageSize=5&filter=${search}`;

  try {
    const rep = await axios.get(productUrl);
    // console.log(rep.data);
    return rep.data.productList;
    //   setProduct(rep.data);
  } catch (error) {
    // console.log(error);
    return error;
  }
};

const SearchProducts = () => {
  const [value, setValue] = useState([]);
  const [form] = Form.useForm();

  return (
    <div>
      <Form form={form} layout="vertical">
        <DebounceSelect
          // mode="single"
          name="search-user"
          label="Tên các thành viên"
          value={value}
          placeholder="Nhập tên thành viên"
          fetchOptions={fetchProduct}
          onChange={(newValue) => {
            setValue(newValue);
          }}
          style={{ width: "100%" }}
        />
      </Form>
    </div>
  );
};

export default SearchProducts;
