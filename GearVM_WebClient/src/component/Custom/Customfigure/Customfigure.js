import React from "react";
import { Link } from "react-router-dom";
import "./Customfigure.css";
const Customfigure = ({ imgUri, Click }) => {
  return (
    <figure className="snip1268">
      <div className="image">
        <img src={imgUri ? imgUri : ""} alt="sq-sample4" />
        <Link href="#" className="add-to-cart" onClick={Click}>
          Click để xem chi tiết
        </Link>
      </div>
    </figure>
  );
};

export default Customfigure;
