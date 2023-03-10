import React, { useState, useEffect, useCallback } from "react";
import styles from "./CatalogProduct.module.scss";
import classNames from "classnames/bind";
import ItemProduct from "../../itemProduct/ItemProduct";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import CheckBox from "../CusCheckbox/CheckBox";
import Pagination from "../Pagination";
import Form from "react-bootstrap/Form";

const cx = classNames.bind(styles);
const CatalogProduct = () => {
  const productList = [
    {
      id: 1,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 1",
      price: 8000000,
      discount: "4",
      colors: ["Trắng", "Đen", "Xanh Dương"],
      brand: "Asus",
    },
    {
      id: 2,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 2",
      price: 15000000,
      discount: "4",
      brand: "Del",
      colors: ["Trắng", "Đỏ", "Xanh Dương"],
    },
    {
      id: 3,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 3",
      price: 20000000,
      discount: "4",
      brand: "MSI",
      colors: ["Trắng", "Đỏ", "Cam"],
    },
    {
      id: 4,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 4",
      price: 12000000,
      discount: "4",
      brand: "Asus",
      colors: ["Trắng", "Đỏ", "Xanh Dương"],
    },
    {
      id: 5,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 5",
      price: 12000000,
      discount: "4",
      brand: "Del",

      colors: ["Trắng", "Vàng", "Xanh Dương"],
    },
    ,
    {
      id: 6,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 6",
      price: 12000000,
      discount: "4",
      brand: "HP",
      colors: ["Trắng", "Đen", "Xanh Dương"],
    },
    {
      id: 7,
      img: "https://betanews.com/wp-content/uploads/2014/11/front.jpg",
      name: "Acer 7",
      price: 12000000,
      discount: "4",
      brand: "HP",
      colors: ["Trắng", "Hồng", "Xanh Dương"],
    },
  ];
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

  const [products, setProducts] = useState(productList);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(4);

  const [filter, setFilter] = useState(initFilter);

  const filterSelect = (type, checked, item) => {
    if (checked) {
      switch (type) {
        case "COLOR":
          setFilter({ ...filter, color: [...filter.color, item.color] });
          break;
        case "BRAND":
          setFilter({ ...filter, brand: [...filter.brand, item.brand] });
          break;
        default:
      }
    } else {
      switch (type) {
        case "COLOR":
          const newColor = filter.color.filter((e) => e !== item.color);
          setFilter({ ...filter, color: newColor });
          break;
        case "BRAND":
          const newBrand = filter.brand.filter((e) => e !== item.brand);
          setFilter({ ...filter, brand: newBrand });
          break;
        default:
      }
    }
  };
  const handleGia = (e) => {
    const temp = [];
    switch (e.target.value) {
      case "tren10":
        setFilter({ ...filter, price: e.target.value });
        products.map((item) => {
          if (item.price > 10000000) {
            temp.push(item);
          }
        });
        setData(temp);

        break;
      case "15-20":
        setFilter({ ...filter, price: e.target.value });

        products.map((item) => {
          if (item.price >= 15000000 && item.price <= 20000000) {
            temp.push(item);
          }
        });
        setData(temp);

        break;
      case "20-25":
        setFilter({ ...filter, price: e.target.value });

        products.map((item) => {
          if (item.price >= 20000000 && item.price <= 25000000) {
            temp.push(item);
          }
        });
        setData(temp);

        break;
      case "25-30":
        setFilter({ ...filter, price: e.target.value });

        products.map((item) => {
          if (item.price >= 25000000 && item.price <= 30000000) {
            temp.push(item);
          }
        });
        setData(temp);

        break;
      case "tren30":
        setFilter({ ...filter, price: e.target.value });

        products.map((item) => {
          if (item.price > 30000000) {
            temp.push(item);
          }
        });
        setData(temp);

        break;
      default:
        break;
    }

    console.log(e.target.value);
  };

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

  console.log(products);
  useEffect(() => {
    updateProducts();
  }, [filter]);

  // Get current posts
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = products.slice(indexOfFirstPost, indexOfLastPost);

  // Change page
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  //handleGia

  return (
    <Container>
      <div className={cx("wrapCatalogProduct")}>
        <div className={cx("wrapCatalogProduct_fillter")}>
          <div className={cx("wrapCatalogProduct_fillter_Price")}>
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
          </div>
          <div className={cx("wrapCatalogProduct_fillter_Price")}>
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
          </div>
          <div className="gach"></div>

          <div className={cx("wrapCatalogProduct_fillter_Price")}>
            <h6 className={cx("txtWrap_Head")}>Màu Sắc</h6>
            <div className={cx("wrapCatalogProduct_fillter_Price_about")}>
              {colors.map((item, index) => (
                <div
                  key={index}
                  className={cx("catalog__filter__widget__content__item")}
                >
                  <CheckBox
                    label={item.color}
                    onChange={(input) =>
                      filterSelect("COLOR", input.checked, item)
                    }
                    checked={filter.color.includes(item.color)}
                  />
                </div>
              ))}
            </div>
          </div>
          <div className="gach"></div>
        </div>
        <div className={cx("wrapCatalogProduct_content")}>
          <Container>
            <Row lg={4} md={3} sm={2}>
              {currentPosts.map((item, index) => (
                <Col key={item.id}>
                  <ItemProduct data={item} />
                </Col>
              ))}
            </Row>
          </Container>
        </div>
      </div>
      <div className={cx("pagination")}>
        <Pagination
          postsPerPage={postsPerPage}
          totalPosts={products.length}
          paginate={paginate}
        />
      </div>
    </Container>
  );
};

export default CatalogProduct;
