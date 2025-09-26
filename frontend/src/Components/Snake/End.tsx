import React from 'react'
import './End.css'

type Props = {
    score: number
    reset: ()=>void;
}

const End = ({score, reset}: Props) => {
  const headers: Headers = new Headers()
  
  headers.set('Content-Type','application/json')

    const postScore = (e: React.FormEvent<HTMLFormElement>)=>{
      e.preventDefault();
      const formData = new FormData(e.currentTarget)
      const name = formData.get("username") as string;
      
      let user = {userScore: score, username:name}
      
      const request: RequestInfo = new Request('http://localhost:8080/score', {
          method: 'POST',
          headers: headers,
          body: JSON.stringify(user)
        })

      return fetch(request).then(res => res.json())
      .then(data => console.log("Score saved:", data))
      .catch(err => console.error(err));
    }

  return (
    <div className='endOverlay'>
        <p>Gameover</p>
        <p>You're Score was {score}</p>

        <form onSubmit={postScore}>
          <p>Enter Name to Save</p>
          <input type="text" id="username" name="username" required></input>
          <br></br>
          <br></br>
          <button type="submit">Sumbit</button>
        </form>
        <br></br>
        <button type="button" onClick={() => reset()}>Reset</button>

    </div>
  )
}

export default End