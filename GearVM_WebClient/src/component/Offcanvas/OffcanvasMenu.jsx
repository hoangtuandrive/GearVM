import React,{useContext} from 'react'
import Offcanvas from 'react-bootstrap/Offcanvas';
import Dropdown from 'react-bootstrap/Dropdown';
import { AppContext } from '../context/AppProvider';

const OffcanvasMenu = () => {
    const {openMenu, setOpenMenu} = useContext(AppContext);
    // const [show, setShow] = useState(false);
    const handleClose = () => setOpenMenu(false);
    // const handleShow = () => setOpenMenu(true);
    console.log(openMenu);
  return (
    <div>
       {/* <Button variant="primary" onClick={handleShow}>
        Launch
      </Button> */}
      <Offcanvas show={openMenu} onHide={handleClose}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>GEARVM</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>
            <Dropdown>
                <Dropdown.Toggle  id="dropdown-basic">
                    Dropdown Button
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
                    <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
                    <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  )
}

export default OffcanvasMenu
