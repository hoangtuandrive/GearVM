import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { publicRoutes } from "././routes";
import AppProvider from "./component/context/AppProvider";
import ModalAddress from "./component/ModalAddress/ModalAddress";
import ModalUser from "./component/ModalUser/ModalUser";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
function App() {
  return (
    <Router>
      <div className="App">
      <AppProvider>
      <ToastContainer />
          <Routes>
            {publicRoutes.map((route, index) => {
              const Page = route.component;
              return <Route key={index} path={route.path} element={<Page />} />;
            })}
          </Routes>
      
        </AppProvider>
      </div>
    </Router>
  );
}
export default App;
