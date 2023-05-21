import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { Pagination } from "antd";
const CustomPagination = ({
  postsPerPage,
  totalPosts,
  paginate,
  pagequery,
}) => {
  let location = useLocation();
  let query = new URLSearchParams(location.search);
  const page = query.get("page");
  const [current, setCurrent] = useState(page ? parseInt(page) : 1);

  useEffect(() => {
    setCurrent(page ? parseInt(page) : 1);
  }, [page]);
  return (
    <nav>
      <ul className="pagination">
        {/* {pageNumbers.map((number) => (
          <li key={number} className="page-item">
            <a onClick={() => paginate(number)} className="page-link">
              {number}
            </a>
          </li>
        ))} */}
        <Pagination
          current={current}
          onChange={(page) => {
            paginate(page);
            setCurrent(page);
          }}
          total={totalPosts * 24}
          pageSize={24}
          showSizeChanger={false}
        />
      </ul>
    </nav>
  );
};

export default CustomPagination;
