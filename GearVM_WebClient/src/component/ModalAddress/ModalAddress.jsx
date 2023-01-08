import React, { useContext, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { AppContext } from '../context/AppProvider';
import styles from'./ModalAddress.module.scss'
import classNames from "classnames/bind";
const cx = classNames.bind(styles);

const ModalAddress = () => {
    const {show, setShow} = useContext(AppContext);
    const handleClose = () => setShow(false);
    return (
        <div className={cx('wrapad')}>
       <Modal show={show} onHide={handleClose} style={{marginTop:100}}>
        <Modal.Header closeButton>
          <Modal.Title>Thông Tin người nhận hàng</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Họ tên</Form.Label>
              <Form.Control
                type="name"
                placeholder="Vui lòng nhập tên người dùng"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                placeholder="Nhập email của bạn"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput3">
              <Form.Label>Số điên thoại</Form.Label>
              <Form.Control
                type="text"
                placeholder="Nhập số điện thoại"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Địa chỉ</Form.Label>
              <Form.Control
                type="address"
                placeholder="Nhập địa chỉ"
                autoFocus
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Hủy bỏ
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Lưu địa chỉ
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
}

export default ModalAddress
