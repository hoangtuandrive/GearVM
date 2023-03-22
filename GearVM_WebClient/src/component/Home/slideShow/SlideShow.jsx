import React, { useState } from "react";
import styles from "./SlideShow.modulo.scss";
import classNames from "classnames/bind";
import { Carousel } from "react-bootstrap";
const cx = classNames.bind(styles);
const SlideShow = () => {
  const [index, setIndex] = useState(0);
  const handleSelect = (selectedIndex, e) => {
    setIndex(selectedIndex);
  };
  return (
    <div className={cx("wrapSlide")}>
      <Carousel activeIndex={index} onSelect={handleSelect}>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src={require("../../../assets/Sale.jpg")}
            alt="First slide"
          />
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src={require("../../../assets/Sale2.jpg")}
            alt="Second slide"
          />
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src={require("../../../assets/Sale3.jpg")}
            alt="Third slide"
          />
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="d-block w-100"
            src={require("../../../assets/slideshow1.jpg")}
            alt="Third slide"
          />
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src={require("../../../assets/slideshow2.jpg")}
            alt="Third slide"
          />
        </Carousel.Item>
      </Carousel>
    </div>
  );
};

export default SlideShow;
