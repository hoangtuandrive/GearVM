import React from 'react'

import classNames from 'classnames/bind'
import styles from './ListProduct.modulo.scss'
import { Image } from 'antd';
import ItemProduct from '../itemProduct/ItemProduct'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";
const cx= classNames.bind(styles)
const ListProduct = (props) => {
  const Laptop=[{
    id:1,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  },
  {
    id:2,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  },{
    id:3,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  },{
    id:4,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  },{
    id:5,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  },
  ,{
    id:6,
    img:'https://betanews.com/wp-content/uploads/2014/11/front.jpg',
    name:'Acer',
    price:'12.000.000',
    discount:'4'
  }]
  console.log(props)
  // const settings = {
  //   dots: true,
  //   infinite: false,
  //   speed: 500,
  //   slidesToShow: 5,
  //   slidesToScroll: 1,
  //   initialSlide: 0,
  //   responsive: [
  //     {
  //       breakpoint: 1024,
  //       settings: {
  //         slidesToShow: 3,
  //         slidesToScroll: 3,
  //         infinite: true,
  //         dots: true
  //       }
  //     },
  //     {
  //       breakpoint: 600,
  //       settings: {
  //         slidesToShow: 2,
  //         slidesToScroll: 2,
  //         initialSlide: 2
  //       }
  //     },
  //     {
  //       breakpoint: 480,
  //       settings: {
  //         slidesToShow: 1,
  //         slidesToScroll: 1
  //       }
  //     }
  //   ]
  // };
  const settings = {
    dots: true,
    infinite: true,
    slidesToShow: 4,
    slidesToScroll: 1,
    autoplay: true,
    speed: 2000,
    autoplaySpeed: 10000,
    cssEase: "linear"
  };
  // console.log(Laptop);
  return (
          <Slider {...settings}  >        
            {props.data.map((item,index)=>(
              <div 
              className={cx('listProduct_thumb_item')}
           
              key={item.id}>       
              <ItemProduct 
                  data={item}
                  />                  
              </div>       
            ))
            }         
      </Slider>    
  )
}
export default ListProduct
