import React from "react";
import Accordion from "react-bootstrap/Accordion";
import ListGroup from "react-bootstrap/ListGroup";
import { Navigate, useNavigate } from "react-router-dom";
const AccordionBody = ({ data }) => {
  const navigate = useNavigate();
  const handleChangePageBrand = (item) => {
    navigate(`/catalog?type=${data.name}&brand=${item}`, {
      replace: true,
    });
  };
  const handleChangePagePrice = (price) => {
    // navigate(`/catalog?type=${data.name}&brand=${item}`, {
    //   replace: true,
    // });

    const priceStrA = parseInt(price.replace(/\D/g, ""));

    if (priceStrA >= 100) {
      const priceStrMin = parseInt(
        price
          .split("-")[0]
          .replace(/^[^0-9]+/, "")
          .replace(/\D/g, "")
      );
      const priceStrMax = parseInt(
        price
          .split("-")[1]
          .replace(/^[^0-9]+/, "")
          .replace(/\D/g, "")
      );
      navigate(
        `/catalog?type=${data.name}&min=${priceStrMin}&max=${priceStrMax}`,
        {
          replace: true,
        }
      );
    } else {
      navigate(`/catalog?type=${data.name}&max=${priceStrA}`, {
        replace: true,
      });
    }
  };

  return (
    <Accordion.Body>
      <Accordion defaultActiveKey="1">
        <Accordion.Item eventKey="11">
          <Accordion.Header>{data.thuongHieu}</Accordion.Header>
          <Accordion.Body>
            <ListGroup>
              {data.listTH.map((item, index) => (
                <ListGroup.Item
                  key={index}
                  onClick={() => handleChangePageBrand(item)}
                  style={{ cursor: "pointer" }}
                >
                  {item}
                </ListGroup.Item>
              ))}
            </ListGroup>
          </Accordion.Body>
        </Accordion.Item>

        <Accordion.Item eventKey="12">
          <Accordion.Header>{data.Gia}</Accordion.Header>
          <Accordion.Body>
            <ListGroup>
              {data.listGia.map((item, index) => (
                <ListGroup.Item
                  key={index}
                  onClick={() => handleChangePagePrice(item)}
                  style={{ cursor: "pointer" }}
                >
                  {item}
                </ListGroup.Item>
              ))}
            </ListGroup>
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
    </Accordion.Body>
  );
};

export default AccordionBody;
