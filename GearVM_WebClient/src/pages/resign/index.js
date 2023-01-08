import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Col, Row, Typography } from "antd";
import IMAGE_ACCOUNT_PAGE from "../../assets/Sale2.jpg";
import "./resign.scss";
import CustomInput from "../../component/Custom/CustomInput";
import { Controller, useForm } from "react-hook-form";

const { Text, Title } = Typography;
function Resign() {
  const navigate = useNavigate();

  const { control, handleSubmit } = useForm();
  
  return (
    <div className="account-common-page">
      <div className="account-wrapper">
        <div className="account_left">
          <img src={IMAGE_ACCOUNT_PAGE} alt="zelo_login" />
        </div>
        <div className="account_right">
          <Title level={2} style={{ textAlign: "center" }}>
            <Text style={{ color: "#4d93ff",fontSize:20}}>Đăng Ký</Text>
          </Title>
          <div className="form-account">
            <Row gutter={[0, 8]}>
              <Col span={18}>
                <CustomInput
                  type="text"
                  name="username"
                  placeholder="Vui lòng nhập họ và tên"
                  control={control}
                  // rules={{ required: "Email is required" }}
                  maxLength={50}
                  titleCol={24}
                  inputCol={18}
                />
              </Col>
              <Col span={18}>
                <CustomInput
                  type="email"
                  name="email"
                  control={control}
                  placeholder="Nhập email"
                  // rules={{
                  //   required: "Password is required",
                  //   minLength: {
                  //     value: 8,
                  //     message: "Password should be minimum 8 characters long",
                  //   },
                  // }}
                  maxLength={200}
                  titleCol={24}
                  inputCol={18}
                />
              </Col>
              <Col span={18}>
                <CustomInput
                  type="text"
                  name="sdt"
                  control={control}
                  placeholder="Nhập số điện thoại"
                  // rules={{
                  //   required: "Password is required",
                  //   minLength: {
                  //     value: 8,
                  //     message: "Password should be minimum 8 characters long",
                  //   },
                  // }}
                  maxLength={200}
                  titleCol={24}
                  inputCol={18}
                />
              </Col>
              <Col span={18}>
                <CustomInput
                  type="text"
                  name="diaChi"
                  control={control}
                  placeholder="Vui lòng nhập địa chỉ"
                  // rules={{
                  //   required: "Password is required",
                  //   minLength: {
                  //     value: 8,
                  //     message: "Password should be minimum 8 characters long",
                  //   },
                  // }}
                  maxLength={200}
                  titleCol={24}
                  inputCol={18}
                />
              </Col>
              <Col span={18}>
                <CustomInput
                  type="password"
                  name="password"
                  control={control}
                  placeholder="Password"
                  // rules={{
                  //   required: "Password is required",
                  //   minLength: {
                  //     value: 8,
                  //     message: "Password should be minimum 8 characters long",
                  //   },
                  // }}
                  maxLength={200}
                  titleCol={24}
                  inputCol={18}
                />
              </Col>
              <Col span={18}>
                <br />
                <Button
                  className="btnLogin"
                  type="primary"
                  htmlType="submit"
                  block
                >
                  Đăng Ký
                </Button>
              </Col>
            </Row>
          </div>

          <div className="addtional-link">
            <Link to="/">Go Back</Link>
            <Link to="/login">Đăng nhập</Link>
            {/* <Link to="/forgotpassword">Forgot Password?</Link> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Resign;
