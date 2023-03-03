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
      const [errorMessage,setErrorMessage]=useState({
        messageEmail:"",
        messagePass:"",
      }); 
useEffect(() => {
        // console.log(auth)
        if (auth.email) {
          navigate("/cart");
        }
      }, [auth.email, navigate]);     
      // console.log(auth);
 const  handleSubmit = (e) => {
    e.preventDefault();

    // console.log(user);
    if( handlePasswrod(user.password) === true && handleExitEmail(user.email) === true)
    {
    dispatch( loginUser(user));
    }
  };
  const handleExitEmail= ()=>{
    const regexEmail= /^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$/;

    if(user.email===''){
      setErrorMessage({...errorMessage,
        messageEmail:'Bạn chưa nhập Email'
        })
        return false;
    } 
    else if(!regexEmail.test(user.email))
    {
   
      setErrorMessage({...errorMessage,
        messageEmail:"Nhập email sai định dạng"
        })
        return false;
    }
    else{
      setErrorMessage({...errorMessage,
        messageEmail:''
        })
        return true;
    }

  }
  console.log(errorMessage);
  const handlePasswrod= ()=>{
    const regexPasswrod= /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
    if(user.password===''){
        setErrorMessage({...errorMessage,
        messagePass:'Bạn chưa nhập mật khẩu'
        })
        return false;
    }
    else if(!regexPasswrod.test(user.password))
    {
   
      setErrorMessage({...errorMessage,
        messagePass:"Mật khẩu nên có 8-20 ký tự và bao gồm ít nhất 1 chữ cái, 1 số và 1 ký tự đặc biệt!"
        })
        return false;
    }
    else{
      setErrorMessage({...errorMessage,
        messagePass:''
        })
        return true;
    }

  }
  return (
    <div className={cx('wrapLogin')}>
        <div className={cx('LoginContent')}>
        <form action='' id='form-login'>
            <h1 className={cx('logintxt')}>Đăng Nhập</h1>
            <div className={cx('form-group')}>
           
                 <input type='text' placeholder='Email' className={cx('form-input')} 
                  onChange={(e) => setUser({ ...user, email: e.target.value })}
                  onBlur={handleExitEmail}
                 />   
            </div>
            {errorMessage.messageEmail===''?null:<span  style={{color:'red'}}>{errorMessage.messageEmail}</span>}
            <div className={cx('form-group')}>
                <input type='password' placeholder='Mật khẩu' className={cx('form-input')} 
                  onChange={(e) => setUser({ ...user, password: e.target.value })}
                  onBlur={handlePasswrod}
                  autoComplete='on'
                />    
            </div>
            {errorMessage.messagePass===''? null :<span  style={{color:'red'}}>{errorMessage.messagePass}</span>}
               
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
