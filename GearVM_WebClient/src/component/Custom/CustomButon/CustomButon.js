import React from "react";
import "./CustomButon.css";
const CustomButon = ({ name, Click }) => {
  return (
    <button className="snip1535" onClick={Click}>
      {name}
    </button>
  );
};

export default CustomButon;
