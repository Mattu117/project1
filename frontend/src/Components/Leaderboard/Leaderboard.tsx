import React from 'react'
import { useState , useEffect, useRef} from "react"

type Props = {}

interface User {
    account_Username: string;
    score: number;
    id: number;
}

export default function Leaderboard({}: Props) {
    const [users,setUsers] = useState<User[]>([])

    async function fetchUsers(): Promise<User[]>{
        const leaderboardURL = import.meta.env.LEADERBOARD_LINK
        const response = await fetch(leaderboardURL);
        const data = await response.json();
        return data;
    }

    useEffect(() => {
        fetchUsers().then((data) => setUsers(data));
        const interval = setInterval(() => {

            fetchUsers().then((data) => setUsers(data));

        },1000);

        return () => clearInterval(interval);
    },[]);   



  return (
    <div>Score History
        {users.map((user)=> (
            <div key={user.id}>
                <h2>{user.account_Username}</h2>
                <p>{user.account_Username} {user.score}</p>
            </div>
        ))}
    </div>
  )
}