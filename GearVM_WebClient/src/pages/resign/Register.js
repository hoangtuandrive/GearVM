import React,{useState} from 'react'
import { Link ,useNavigate} from 'react-router-dom';
import classNames from 'classnames/bind';
import styles from "../login/login.scss";
import { useDispatch,useSelector } from 'react-redux';
import { registerUser,loginUser } from '../../redux/slices/AuthSlices';
import { useEffect } from 'react';
const cx= classNames.bind(styles);

const Register = () => {
  const dispatch=useDispatch();
  const navigate=useNavigate();
  const auth = useSelector((state) => state.auth);
  const [user, setUser] = useState({
    name:"",
    dateOfBirth:"",
    gender:"",
    phoneNumber:"",
    email: "",
    password: "",
  });
  console.log(auth);
useEffect(()=>{
  if(auth.registerStatus==='succes'){
    dispatch( loginUser(user));
    navigate("/cart")
  }
},[auth.registerStatus]);
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(user);
    dispatch(registerUser(user));
    
  };
  return (
    <div className={cx('wrapLogin')}>
        <div className={cx('LoginContent')}>
        <form action='' id='form-login'>
            <h1 className={cx('logintxt')}>Đăng Ký</h1>

            <div className={cx('form-group')}>
                 <input type='text' placeholder='Họ và Tên' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, name: e.target.value })}

                 />   
            </div>

            <div className={cx('form-group')}>
                 <input type='date' placeholder='dateOfBirth' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, dateOfBirth: e.target.value })}
              />   
            </div>

            <div className={cx('form-group')}>
                 <input type='text' placeholder='Email' className={cx('form-input')}
                onChange={(e) => setUser({ ...user, email: e.target.value })}
                 
                 />   
            </div>

            <div className={cx('form-group')}>
                 <input type='phone' placeholder='Số điện thoại' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, phoneNumber: e.target.value })}
                 
                 />   
            </div>
            <div className={cx('form-check-group')}>
                <div className="form-check">
                    <input className="form-check-input" type="radio" name="gioiTinh" id="Nam" 
                     checked={user.gender=== 'MALE' ? true : false} 
                  onChange={(e) => setUser({ ...user, gender:'MALE' })}
                     />
                    <label className="form-check-label" htmlFor="Nam">
                     Nam
                    </label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="radio" name="gioiTinh" id="Nu" 
                    checked={user.gender=== 'FEMALE'? true : false} 
                  onChange={(e) =>{
                    setUser({ ...user, gender: "FEMALE" })}
                    
                  } 
                    
                    />
                    <label className="form-check-label" htmlFor="Nu">
                      Nữ
                    </label>
                </div>
                <div className="form-check">
                    <input className="form-check-input" type="radio" name="gioiTinh" id="Khac"  
                    checked={user.gender=== 'UNDEFINED'? true: false} 
                  onChange={(e) => setUser({ ...user, gender: 'UNDEFINED' })}
                    />
                    <label className="form-check-label" htmlFor="Khac">
                      Khác
                    </label>
                </div>
            </div>
            <div className={cx('form-group')}>
                <input type='password' placeholder='Mật khẩu' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, password: e.target.value })}
                autoComplete='on'
                />    
            </div>
               
            <input type="submit" value="Đăng ký"  className={cx('form-submit')}
            onClick={handleSubmit}
            />
            <div className="addtional-link">             
            <Link to="/">Go Back</Link>
            <Link to="/login">Đăng Nhập</Link>
            {/* <Link to="/forgotpassword">Forgot Password?</Link> */}
           </div>
        </form>
        </div>
    </div>
  )
}

export default Register
