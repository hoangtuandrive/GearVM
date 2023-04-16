import React, { useContext } from "react";
import Header from "../component/Home/header/Header";
import Footer from "../component/Home/footer/Footer";
import styles from "../sass/Catalog.modulo.scss";
import classNames from "classnames/bind";
import CatalogProduct from "../component/Catalog/catalogProduct/CatalogProduct";
import { AppContext } from "../component/context/AppProvider";
import ChatBox from "../component/chatBox/ChatBox";
import ScrolltoTop from "../component/Home/ScrolltoTop/ScrolltoTop";
const cx = classNames.bind(styles);
const Catalog = () => {
  const { showChat } = useContext(AppContext);
  return (
    <div className={cx("wrapCatalog")}>
      <Header />
      <CatalogProduct />
      <ScrolltoTop />
      {showChat ? <ChatBox /> : null}
      <Footer />
    </div>
  );
};

export default Catalog;
