import React,{useState} from 'react'
import { Link ,useNavigate} from 'react-router-dom';
import classNames from 'classnames/bind';
import styles from "../login/login.scss";
import { useDispatch,useSelector } from 'react-redux';
import { registerUser,loginUser,exitEmail } from '../../redux/slices/AuthSlices';
import { useEffect } from 'react';
const cx= classNames.bind(styles);

const Register = () => {
  const dispatch=useDispatch();
  const navigate=useNavigate();
  const auth = useSelector((state) => state.auth);
  const [user, setUser] = useState({
    name:"",
    dateOfBirth:"",
    gender:"MALE",
    phoneNumber:"",
    email: "",
    password: "",
  });
  const [errorMessage,setErrorMessage]=useState({
    id:"",
    message:"",
  })
  // console.log(auth);
useEffect(()=>{
  if(auth.registerStatus==='succes'){
    dispatch( loginUser(user));
    navigate("/cart")
  }
  
},[auth.registerStatus]);
  const handleSubmit = (e) => {
    e.preventDefault();
    // console.log(user);
    if(handleName(user.email) === true && handlePasswrod(user.password) === true 
       && handlePhone(user.phoneNumber) === true && handleExitEmail(user.email) === true
    ){
     dispatch(registerUser(user));
    }
    
     
  };
  const handleName = ()=>{
    const regexName= /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/;
      if(user.name===''){
          setErrorMessage({...errorMessage,
          id:"1",
          message:'Bạn chưa nhập Họ tên'
          })
          return false;
      }  
      else if(!regexName.test(user.name))
      {
     
        setErrorMessage({...errorMessage,
          id:"1",
          message:"Tên người dùng phải có 3-16 ký tự và không được bao gồm bất kỳ ký tự đặc biệt nào!"
          })
          return false;
      }
      else{
        setErrorMessage({...errorMessage,
          id:"1",
          message:''
          })
          return true;
      }
      
  }
  const handleExitEmail= ()=>{
    const regexEmail= /^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$/;

    if(user.email===''){
        setErrorMessage({...errorMessage,
        id:"2",
        message:'Bạn chưa nhập Email'
        })
        return false;
    } 
    else if(!regexEmail.test(user.email))
    {
   
      setErrorMessage({...errorMessage,
        id:"2",
        message:"Nhập email sai định dạng"
        })
        return false;
    }
    else if(auth.emailStatus === true){
      console.log(123);
      setErrorMessage({...errorMessage,
        id:"2",
        message:"Email đã tồn tại vui lòng nhập lại email khác"
        })
        return false;
    }
    else{
      setErrorMessage({...errorMessage,
        id:"2",
        message:''
        })
        return true;
    }

  
  }
const handlePhone= ()=>{
  const regexPhone= /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    if(user.phoneNumber===''){
        setErrorMessage({...errorMessage,
        id:"3",
        message:'Bạn chưa nhập số điện thoại'
        })
        return false;
    }
    else if(!regexPhone.test(user.phoneNumber))
    {
   
      setErrorMessage({...errorMessage,
        id:"3",
        message:"Nhập số điện thoại sai định dạng"
        })
        return false;
    }
    else{
      setErrorMessage({...errorMessage,
        id:"3",
        message:''
        })
        return true;
    }

  }
  const handlePasswrod= ()=>{
    const regexPasswrod= /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
    if(user.password===''){
        setErrorMessage({...errorMessage,
        id:"4",
        message:'Bạn chưa nhập mật khẩu'
        })
        return false;
    }
    else if(!regexPasswrod.test(user.password))
    {
   
      setErrorMessage({...errorMessage,
        id:"4",
        message:"Mật khẩu nên có 8-20 ký tự và bao gồm ít nhất 1 chữ cái, 1 số và 1 ký tự đặc biệt!"
        })
        return false;
    }
    else{
      setErrorMessage({...errorMessage,
        id:"4",
        message:''
        })
        return true;
    }

  }
  return (
    <div className={cx('wrapLogin')}>
        <div className={cx('LoginContent')}>
        <form action='' id='form-login'>
            <h1 className={cx('logintxt')}>Đăng Ký</h1>

            <div className={cx('form-group')}>
                 <input type='text' placeholder='Họ và Tên' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, name: e.target.value })}
                onBlur={handleName}
                
                 />   
                
            </div>
            {errorMessage.id==='1'?<span  style={{color:'red'}}>{errorMessage.message}</span>:null}
            <div className={cx('form-group')}>
                 <input type='date' placeholder='dateOfBirth' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, dateOfBirth: e.target.value })}
              />   
            </div>
           
            <div className={cx('form-group')}>
                 <input type='text' placeholder='Email' className={cx('form-input')}
                onChange={(e) => {
                  setUser({ ...user, email: e.target.value })
                  dispatch(exitEmail(e.target.value));
                }}
                 onBlur={handleExitEmail}
                 />   
            </div>
            {errorMessage.id==='2'?<span  style={{color:'red'}}>{errorMessage.message}</span>:null}
            <div className={cx('form-group')}>
                 <input type='phone' placeholder='Số điện thoại' className={cx('form-input')} 
                onChange={(e) => setUser({ ...user, phoneNumber: e.target.value })}
                onBlur={handlePhone}
                 />   
            </div>
            {errorMessage.id==='3'?<span  style={{color:'red'}}>{errorMessage.message}</span>:null}
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
                onBlur={handlePasswrod}
                autoComplete='on'
                />    
            </div>
            {errorMessage.id==='4'?<span style={{color:'red'}}>{errorMessage.message}</span>:null}
               
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
