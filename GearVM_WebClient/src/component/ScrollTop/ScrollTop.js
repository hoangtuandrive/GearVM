import { useEffect } from "react";

function ScrollTop() {
  useEffect(() => {
    // Scroll to top when the component mounts
    window.scrollTo(0, 0);
  }, []);

  return null;
}

export default ScrollTop;
