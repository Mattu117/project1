import React from 'react'
import './LoginForm.css'

const LoginForm = () => {
  return (
    <>
    <div className='container'>
      <div className="header">
        <div className='text'>Sign up</div>
        <div className='underline'></div>
    </div>
    <div className="inputs">
        <div className='input'>
          Username: 
            <input type="username"/>
        </div>
        <div className='input'>
          Password: 
            <input type="passoword"/>
        </div>
    </div>
    <div className='forgot-password'>Forgot password? <span>Click Here</span></div>
    <div className='submit-container'>
      <div className='Submit'>Sign up</div>
      <div className='Submit'>Login</div>
    </div>
    </div>
    
    </>
  )
}

export default LoginForm