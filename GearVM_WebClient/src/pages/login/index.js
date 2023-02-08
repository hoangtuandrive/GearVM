import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Col, Row, Typography } from "antd";
import IMAGE_ACCOUNT_PAGE from "../../assets/Sale2.jpg";
import "./login.scss";
import CustomInput from "../../component/Custom/CustomInput";
import { Controller, useForm } from "react-hook-form";
import { useState } from "react";
import { loginUser } from "../../redux/slices/AuthSlices";
import { useDispatch } from "react-redux";
const { Text, Title } = Typography;
function Login() {
  const dispatch= useDispatch();
  // const navigate = useNavigate();

  // const { control } = useForm();
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  
 const  handleSubmit = (e) => {
    e.preventDefault();

    console.log(user);
    dispatch( loginUser(user));
  };

  
  return (
    <div className="account-common-page">
      <div className="account-wrapper">
        <div className="account_left">
          <img src={IMAGE_ACCOUNT_PAGE} alt="zelo_login" />
        </div>
        <div className="account_right">
          <Title level={2} style={{ textAlign: "center" }}>
            <Text style={{ color: "#4d93ff",fontSize:20 }}>Đăng Nhập</Text>
          </Title>
          <div className="form-account">
            <Row gutter={[0, 8]}>
              <Col span={18}>
                <input
                  type="text"
                  name="username"
                  onChange={(e) => setUser({ ...user, email: e.target.value })
                }
                  placeholder="Email"
                  // control={control}
                  // rules={{ required: "Email is required" }}
                  // maxLength={50}
                  // titleCol={24}
                  // inputCol={18}
                />
              </Col>
              <Col span={18}>
                <input
                  type="password"
                  name="password"
                  onChange={(e) => setUser({ ...user, password: e.target.value })}
                  // control={control}
                  placeholder="Password"
                  // rules={{
                  //   required: "Password is required",
                  //   minLength: {
                  //     value: 8,
                  //     message: "Password should be minimum 8 characters long",
                  //   },
                  // }}
                  // maxLength={200}
                  // titleCol={24}
                  // inputCol={18}
                />
              </Col>
              <Col span={18}>
                <br />
                <Button
                  className="btnLogin"
                  type="primary"
                  htmlType="submit"
                  block
                  onClick={handleSubmit}
                >
                  Đăng Nhập
                </Button>
              </Col>
              <Col span={18}>
                <Button className="btnLogin" htmlType="submit" block>
                  {" "}
                  Continue with Facebook!
                </Button>
              </Col>{" "}
              <br />
              <Col span={18}>
                <Button className="btnLogin" htmlType="submit" block>
                  {" "}
                  Continue with Google!
                </Button>
              </Col>
            </Row>
          </div>

          <div className="addtional-link">
            <Link to="/">Go Back</Link>
            <Link to="/resign">Đăng ký</Link>
            {/* <Link to="/forgotpassword">Forgot Password?</Link> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
