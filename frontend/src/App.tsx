import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import LoginForm from './Components/Login/LoginForm'
import Snake from './Components/Snake/Snake'
import Leaderboard from './Components/Leaderboard/Leaderboard'


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Snake/>
      <Leaderboard/>
    </>
  )
}

export default App
