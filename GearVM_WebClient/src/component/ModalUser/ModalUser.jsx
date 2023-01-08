import React, { useContext, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { AppContext } from '../context/AppProvider';
import styles from'./ModalUser.module.scss'
import classNames from "classnames/bind";
const cx = classNames.bind(styles);

const ModalUser = () => {
    const {UserOpen, setUserOpen} = useContext(AppContext);
    const handleClose = () => setUserOpen(false);
    return (
        <div className={cx('wrapad')}>
       <Modal show={UserOpen} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Thông Tin người dùng</Modal.Title>
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
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput4">
              <Form.Label>Địa chỉ</Form.Label>
              <Form.Control
                type="address"
                placeholder="Nhập địa chỉ"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput5">
              <Form.Label>Ngày Sinh</Form.Label>
              <Form.Control
                type="date"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput6">
              <Form.Label>Giới Tính</Form.Label>
             <div>
              <input  type='radio'/>
             </div>
            
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Hủy bỏ
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Cập nhật
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
}

export default ModalUser
