import React, { useState } from "react";

export const AppContext = React.createContext();
function AppProvider({ children }) {
  const [show, setShow] = useState(false);
  const [UserOpen, setUserOpen] = useState(false);
  const [openMenu, setOpenMenu] = useState(false);
  const [showChat, setShowChat] = useState(false);

  const clearState = () => {
    setShow(false);
    setUserOpen(false);
    setOpenMenu(false);
    setShowChat(false);
  };
  return (
    <AppContext.Provider
      value={{
        show,
        setShow,
        clearState,
        UserOpen,
        setUserOpen,
        openMenu,
        setOpenMenu,
        showChat,
        setShowChat,
      }}
    >
      {children}
    </AppContext.Provider>
  );
}

export default AppProvider;
