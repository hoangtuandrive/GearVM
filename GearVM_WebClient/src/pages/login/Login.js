import React,{ useState,useEffect } from 'react'
import { Link,useNavigate } from 'react-router-dom';
import classNames from 'classnames/bind';
import styles from "./login.scss"
import { useDispatch ,useSelector} from 'react-redux';
import { loginUser } from '../../redux/slices/AuthSlices';
const cx= classNames.bind(styles);
const Login = () => {
    const dispatch=useDispatch();
    const navigate= useNavigate();
  const auth = useSelector((state) => state.auth);

    const [user, setUser] = useState({
        email: "",
        password: "",
      });
useEffect(() => {
        console.log(auth)
        if (auth.email) {
          navigate("/cart");
        }
      }, [auth.email, navigate]);     
      console.log(auth);
 const  handleSubmit = (e) => {
    e.preventDefault();

    console.log(user);
    dispatch( loginUser(user));
  };
  return (
    <div className={cx('wrapLogin')}>
        <div className={cx('LoginContent')}>
        <form action='' id='form-login'>
            <h1 className={cx('logintxt')}>Đăng Nhập</h1>
            <div className={cx('form-group')}>
           
                 <input type='text' placeholder='Email' className={cx('form-input')} 
                  onChange={(e) => setUser({ ...user, email: e.target.value })}
                 />   
            </div>
            <div className={cx('form-group')}>
                <input type='password' placeholder='Mật khẩu' className={cx('form-input')} 
                  onChange={(e) => setUser({ ...user, password: e.target.value })}
                  autoComplete='on'
                />    
            </div>
               
            <input type="submit" value={auth.loginStatus === "Chờ" ? "Submitting..." : "Đăng Nhập"} className={cx('form-submit')} 
                  onClick={handleSubmit}
            />
            {auth.loginStatus === "rejected" ? <p className={cx('txtError')}>{auth.loginError}</p> : null}
            <div className="addtional-link">             
            <Link to="/">Go Back</Link>
            <Link to="/resign">Đăng ký</Link>
            {/* <Link to="/forgotpassword">Forgot Password?</Link> */}
           </div>
        </form>
        </div>
    </div>
  )
}

export default Login
