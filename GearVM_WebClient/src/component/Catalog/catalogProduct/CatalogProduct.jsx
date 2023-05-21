import React, { useState, useEffect, useCallback } from "react";
import { Slider, Tooltip } from "antd";
import styles from "./CatalogProduct.module.scss";
import classNames from "classnames/bind";
import ItemProduct from "../../itemProduct/ItemProduct";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import CheckBox from "../CusCheckbox/CheckBox";

import Form from "react-bootstrap/Form";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import CustomPagination from "../CustomPagination";

import FilterCatalog from "../filterCatalog/FilterCatalog";
import { url } from "../../../API/api";
import dataNavModal from "../../../dataUI/dataNavModal";

const cx = classNames.bind(styles);
const CatalogProduct = () => {
  // const productList = [
  //   {
  //     id: 1,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 1",
  //     price: 8000000,
  //     discount: "4",
  //     colors: ["Trắng", "Đen", "Xanh Dương"],
  //     brand: "Asus",
  //   },
  //   {
  //     id: 2,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 2",
  //     price: 15000000,
  //     discount: "4",
  //     brand: "Del",
  //     colors: ["Trắng", "Đỏ", "Xanh Dương"],
  //   },
  //   {
  //     id: 3,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 3",
  //     price: 20000000,
  //     discount: "4",
  //     brand: "MSI",
  //     colors: ["Trắng", "Đỏ", "Cam"],
  //   },
  //   {
  //     id: 4,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 4",
  //     price: 12000000,
  //     discount: "4",
  //     brand: "Asus",
  //     colors: ["Trắng", "Đỏ", "Xanh Dương"],
  //   },
  //   {
  //     id: 5,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 5",
  //     price: 12000000,
  //     discount: "4",
  //     brand: "Del",

  //     colors: ["Trắng", "Vàng", "Xanh Dương"],
  //   },
  //   ,
  //   {
  //     id: 6,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 6",
  //     price: 12000000,
  //     discount: "4",
  //     brand: "HP",
  //     colors: ["Trắng", "Đen", "Xanh Dương"],
  //   },
  //   {
  //     id: 7,
  //     img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
  //     name: "Acer 7",
  //     price: 12000000,
  //     discount: "4",
  //     brand: "HP",
  //     colors: ["Trắng", "Hồng", "Xanh Dương"],
  //   },
  // ];
  const colors = [
    {
      color: "Trắng",
    },
    {
      color: "Hồng",
    },
    {
      color: "Đen",
    },
    {
      color: "Vàng",
    },
    {
      color: "Cam",
    },
    {
      color: "Xanh dương",
    },
  ];
  const brands = [
    {
      brand: "Asus",
    },
    {
      brand: "MSI",
    },
    {
      brand: "Del",
    },
    {
      brand: "HP",
    },
  ];
  const initFilter = {
    color: [],
    brand: [],
    price: "",
  };
  const navigate = useNavigate();
  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const page = query.get("page");
  const filed = query.get("filed");
  const type = query.get("type");
  const brand = query.get("brand");
  const min = query.get("min");
  const max = query.get("max");
  const price_gte = query.get("price_gte");
  const price_lte = query.get("price_lte");

  const [productList, setProductList] = useState([]);
  const [products, setProducts] = useState(productList);
  const [totalPage, setTotalPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(4);

  const [minValue, set_minValue] = useState(min ? min : 0);
  const [maxValue, set_maxValue] = useState(max ? max : 100);
  // const handleInput = (e) => {
  //   set_minValue(e.minValue);
  //   set_maxValue(e.maxValue);
  // };

  const [filter, setFilter] = useState(initFilter);

  // const filterSelect = (type, checked, item) => {
  //   console.log(checked);
  //   if (checked) {
  //     switch (type) {
  //       case "COLOR":
  //         setFilter({ ...filter, color: [...filter.color, item.color] });
  //         break;
  //       case "BRAND":
  //         setFilter({ ...filter, brand: [...filter.brand, item.brand] });
  //         break;
  //       default:
  //     }
  //   } else {
  //     switch (type) {
  //       case "COLOR":
  //         const newColor = filter.color.filter((e) => e !== item.color);
  //         setFilter({ ...filter, color: newColor });
  //         break;
  //       case "BRAND":
  //         const newBrand = filter.brand.filter((e) => e !== item.brand);
  //         setFilter({ ...filter, brand: newBrand });
  //         break;
  //       default:
  //     }
  //   }
  // };
  // const handleGia = (e) => {
  //   const temp = [];
  //   switch (e.target.value) {
  //     case "tren10":
  //       setFilter({ ...filter, price: e.target.value });
  //       products.map((item) => {
  //         if (item.price > 10000000) {
  //           temp.push(item);
  //         }
  //       });
  //       setData(temp);

  //       break;
  //     case "15-20":
  //       setFilter({ ...filter, price: e.target.value });

  //       products.map((item) => {
  //         if (item.price >= 15000000 && item.price <= 20000000) {
  //           temp.push(item);
  //         }
  //       });
  //       setData(temp);

  //       break;
  //     case "20-25":
  //       setFilter({ ...filter, price: e.target.value });

  //       products.map((item) => {
  //         if (item.price >= 20000000 && item.price <= 25000000) {
  //           temp.push(item);
  //         }
  //       });
  //       setData(temp);

  //       break;
  //     case "25-30":
  //       setFilter({ ...filter, price: e.target.value });

  //       products.map((item) => {
  //         if (item.price >= 25000000 && item.price <= 30000000) {
  //           temp.push(item);
  //         }
  //       });
  //       setData(temp);

  //       break;
  //     case "tren30":
  //       setFilter({ ...filter, price: e.target.value });

  //       products.map((item) => {
  //         if (item.price > 30000000) {
  //           temp.push(item);
  //         }
  //       });
  //       setData(temp);

  //       break;
  //     default:
  //       break;
  //   }

  //   console.log(e.target.value);
  // };

  const handleChangePrice = (e) => {
    set_minValue(e[0]);
    set_maxValue(e[1]);
    if (brand) {
      const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=5&brand=${brand}&type=${type}&min=${
        e[0] * 1000000
      }&max=${e[1] * 1000000}`;
      fetchProduct(productUrl);
    } else if (max) {
      const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=24&type=${type}&min=${
        e[0] * 1000000
      }&max=${e[1] * 1000000}`;
      fetchProduct(productUrl);
    } else {
      const CheckFiled = (value) => {
        return dataNavModal.map((item) => {
          return item.name === value;
        });
      };
      if (CheckFiled(filed)) {
        const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=24&type=${filed}&min=${
          e[0] * 1000000
        }&max=${e[1] * 1000000}`;
        fetchProduct(productUrl);
      } else {
        const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=24&brand=${filed}&min=${
          e[0] * 1000000
        }&max=${e[1] * 1000000}`;
        fetchProduct(productUrl);
      }
    }
  };
  console.log(maxValue);
  console.log(minValue);
  console.log(max ? max : 100);
  const updateProducts = useCallback(() => {
    let temp = productList;
    if (filter.color.length > 0) {
      temp = temp.filter((e) => {
        const check = e.colors.find((color) => filter.color.includes(color));
        console.log(check);
        return check !== undefined;
      });
    }
    if (filter.brand.length > 0) {
      temp = temp.filter((e) => filter.brand.includes(e.brand));
    }
    // if (filter.price === "tren10") {
    //   // console.log(1);
    // }

    if (filter.price.length > 0 && filter.price !== "tren10") {
      temp = data;
    }

    setProducts(temp);
  }, [filter, productList]);
  const fetchProduct = async (productUrl) => {
    try {
      const rep = await axios.get(productUrl);
      // setProductList(rep.data.productList);
      setProducts(rep.data.productList);
      setTotalPage(rep.data.totalPage);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    if (price_gte || price_lte) {
      set_maxValue(price_lte);
      set_minValue(price_gte ? price_gte : 0);
    } else {
      set_maxValue(max ? max : 100);
      set_minValue(min ? min : 0);
      console.log(brand, page);
    }

    if (page && brand) {
      const productUrl = `${url}/products/filter-search-price?pageNumber=${
        page - 1
      }&pageSize=5&brand=${brand}&type=${type}&min=${minValue * 1000000}&max=${
        maxValue * 1000000
      }`;
      // console.log(type, brand, page);
      // console.log("chay");
      fetchProduct(productUrl);
    } else if (page && filed) {
      // const productUrl = `${url}/products/search-bar?pageNumber=${
      //   page - 1
      // }&pageSize=24&filter=${filed}`;
      // fetchProduct(productUrl);
      const CheckFiled = (value) => {
        return dataNavModal.map((item) => {
          return item.name === value;
        });
      };
      if (CheckFiled(filed)) {
        const productUrl = `${url}/products/filter-search-price?pageNumber=${
          page - 1
        }&pageSize=24&type=${filed}&min=${minValue * 1000000}&max=${
          maxValue * 1000000
        }`;
        fetchProduct(productUrl);
      } else {
        const productUrl = `${url}/products/filter-search-price?pageNumber=${
          page - 1
        }&pageSize=24&brand=${filed}&min=${minValue * 1000000}&max=${
          maxValue * 1000000
        }`;
        fetchProduct(productUrl);
      }
    } else if (page && max) {
      const productUrl = `${url}/products/filter-search-price?pageNumber=${
        page - 1
      }&pageSize=24&type=${type}&min=${minValue * 1000000}&max=${
        maxValue * 1000000
      }`;
      fetchProduct(productUrl);
    } else if (page) {
      const productUrl = `${url}/products/search-bar?pageNumber=${
        page - 1
      }&pageSize=24&filter=${filed}`;

      fetchProduct(productUrl);
    } else if (brand) {
      const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=5&brand=${brand}&type=${type}`;
      fetchProduct(productUrl);
    } else if (max) {
      console.log(13);
      const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=2&type=${type}&min=${
        min * 1000000
      }&max=${max * 1000000}`;
      fetchProduct(productUrl);
    } else {
      const productUrl = `${url}/products/search-bar?pageNumber=0&pageSize=24&filter=${filed}`;
      fetchProduct(productUrl);
    }

    // if (page) {
    //   if (brand) {
    //     const productUrl = `${url}/products/filter-search-price?pageNumber=${
    //       page - 1
    //     }&pageSize=24&brand=${brand}&type=${type}`;
    //     fetchProduct(productUrl);
    //   } else {
    //     const productUrl = `${url}/products/search-bar?pageNumber=${
    //       page - 1
    //     }&pageSize=24&filter=${filed}`;
    //     fetchProduct(productUrl);
    //   }
    // } else {
    //   if (brand) {
    //     const productUrl = `${url}/products/filter-search-price?pageNumber=0&pageSize=24&brand=${brand}&type=${type}`;
    //     fetchProduct(productUrl);
    //   } else {
    //     const productUrl = `${url}/products/search-bar?pageNumber=0&pageSize=24&filter=${filed}`;
    //     fetchProduct(productUrl);
    //   }
    // }

    updateProducts();
  }, [filter, filed, min, max, brand, type, page]);

  // Get current posts
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  // const currentPosts = products.slice(indexOfFirstPost, indexOfLastPost);

  // Change page
  const paginate = (pageNumber) => {
    if (brand) {
      navigate(
        `/catalog?page=${pageNumber}&brand=${brand}&type=${type}&price_gte=${minValue}&price_lte=${maxValue}`
      );
    } else if (filed) {
      navigate(
        `/catalog?page=${pageNumber}&filed=${filed}&price_gte=${minValue}&price_lte=${maxValue}`
      );
    } else if (max) {
      navigate(
        `/catalog?page=${pageNumber}&type=${type}&min=${min}&max=${max}&price_gte=${minValue}&price_lte=${maxValue}`
      );
    }
    // const fetchProduct = async () => {
    //   const productUrl = `${url}/products/search-bar?pageNumber=${
    //     pageNumber - 1
    //   }&pageSize=24&filter=${filed}`;
    //   console.log(filed);
    //   try {
    //     const rep = await axios.get(productUrl);
    //     setProducts(rep.data.productList);
    //     setTotalPage(rep.data.totalPage);
    //     console.log(rep.data.productList);
    //   } catch (error) {
    //     console.log(error);
    //   }
    //   console.log(products);
    // };
    // fetchProduct();
    setCurrentPage(pageNumber);
  };
  //handleGia

  return (
    <Container fluid="md">
      {/* <FilterCatalog /> */}
      <div className={cx("wrapCatalogProduct")}>
        <div className={cx("wrapCatalogProduct_fillter")}>
          {/* <div className={cx("wrapCatalogProduct_fillter_Price")}>
            <h6 className={cx("txtWrap_Head")}>Lọc theo giá</h6>
            <div className={cx("wrapCatalogProduct_fillter_Price_about")}>
              <Form.Select
                aria-label="Default select example"
                onChange={handleGia}
              >
                <option value="tren10">Trên 10 triệu</option>
                <option value="15-20">Từ 15 triệu -20 triệu</option>
                <option value="20-25">Từ 20 triệu -25 triệu</option>
                <option value="25-30">Từ 25 triệu -30 triệu</option>
                <option value="tren30">Trên 30 Triệu</option>
              </Form.Select>
            </div>
          </div>  */}
          {/* <div className={cx("wrapCatalogProduct_fillter_Price")}>
            <h6 className={cx("txtWrap_Head")}>Thương Hiệu</h6>
            <div className={cx("wrapCatalogProduct_fillter_Price_about")}>
              {brands.map((item, index) => (
                <div
                  key={index}
                  className={cx("catalog__filter__widget__content__item")}
                >
                  <CheckBox
                    label={item.brand}
                    onChange={(input) =>
                      filterSelect("BRAND", input.checked, item)
                    }
                    checked={filter.brand.includes(item.brand)}
                  />
                </div>
              ))}
            </div>
          </div> */}
          {/* <div className="gach"></div> */}
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
              autoFocus={true}
              tooltip={{ open: false }}
              range
              min={parseFloat(min ? min : 0)}
              max={parseFloat(max ? max : 100)}
              // defaultValue={[
              //   parseInt(min ? min : 0),
              //   parseInt(max ? max : 100),
              // ]}
              defaultValue={[0, 100]}
              onChange={handleChangePrice}
              className={cx("changePrice")}
            />
          </div>
        </div>
        {/* <div className={cx("wrapCatalogProduct_content")}> */}
        <Container fluid="md">
          <Row lg={4 | "auto"} md={3} sm={2}>
            {products?.map((item, index) => (
              <Col key={item.id}>
                <ItemProduct data={item} />
              </Col>
            ))}
          </Row>
        </Container>
        {/* </div> */}
      </div>
      <div className={cx("pagination")}>
        <CustomPagination
          postsPerPage={postsPerPage}
          totalPosts={totalPage}
          paginate={paginate}
          pagequery={page}
        />
      </div>
    </Container>
  );
};

export default CatalogProduct;
