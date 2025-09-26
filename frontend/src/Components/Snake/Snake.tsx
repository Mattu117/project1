import React from 'react'
import { useState , useEffect, useRef} from "react"
import End from './End.tsx'

const Snake = () => {
    let startingCoords = [{x: 360, y:180}, {x: 360, y:195}, {x: 360, y:195}, {x: 360, y:195}]
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const [score, setScore] = useState(0)
    const [snake, setSnake] = useState<coordinates[]>(startingCoords)
    const [food, setFood] = useState<coordinates>(randomFood)
    const [move, setMove] = useState("")
    const [gameover,setGameover] = useState(false)

    type coordinates = {
        x: number;
        y: number;
    }
    const squareSize = 15;
    const canvasHeight = 450;
    const canvasWidth = 750;



    useEffect(() => {
        const canvas = canvasRef.current
        if(!canvas) return;
        const ctx = canvas.getContext("2d") 
        if(!ctx) return;

        ctx.fillStyle = '#00000'
        ctx.clearRect(0, 0, canvas.width, canvas.height)
        ctx.fillStyle = 'Red'
        ctx.fillRect(food.x,food.y, squareSize, squareSize)
        ctx.fillStyle = 'Green'
        snake.forEach(segment => ctx.fillRect(segment.x,segment.y, squareSize, squareSize))
    },[snake])


    useEffect(() => {
        function handleKeyDown(e: KeyboardEvent){
            if(gameover){return}
            switch (move){
                case 'ArrowUp':
                    if(e.key === 'ArrowDown'){
                        break;
                    }
                    setMove(e.key);
                    break;
                case 'ArrowDown':
                    if(e.key === 'ArrowUp'){
                        break;
                    }
                    setMove(e.key);
                    break;
                case 'ArrowRight':
                    if(e.key === 'ArrowLeft'){
                        break;
                    }
                    setMove(e.key);
                    break;
                case 'ArrowLeft':
                    if(e.key === 'ArrowRight'){
                        break;
                    }
                    setMove(e.key);
                    break;
                default:
                    setMove(e.key);
                    break;
            }
        }

        
        window.addEventListener("keydown",handleKeyDown);
        
        return () => window.removeEventListener("keydown",handleKeyDown);
    })

    useEffect(() => {
        const interval = setInterval(() =>{
            switch (move){
                case 'ArrowUp':
                    moveSnake({dx:0, dy:-15})
                    break;
                case 'ArrowDown':
                    moveSnake({dx:0, dy:15})
                    break;
                case 'ArrowRight':
                    moveSnake({dx:15, dy:0})
                    break;
                case 'ArrowLeft':
                    moveSnake({dx:-15, dy:0})
                    break;
                default:
                    break;
            }
        },75)
        return () => clearInterval(interval)
    })

    function end(){
        setGameover(true);
    }

    function moveSnake({dx, dy}: {dx: number, dy: number}){
        if(snake[0].y >= canvasHeight || snake[0].y <= -squareSize || snake[0].x >= canvasWidth || snake[0].x <= -squareSize || gameover){
            end();
            return;
        }
        let newSnake = [{x: snake[0].x + dx, y: snake[0].y + dy}, ...snake]

        if(snake[0].x === food.x && snake[0].y === food.y){
            setScore(score + 100)
            setFood(randomFood) 
        }
        else{
            newSnake = newSnake.slice(0,-1)
        }
        snake.forEach((segment,index) => {if(index !== 0 && snake[0].x === segment.x && snake[0].y === segment.y){ end(); return;}})
        setSnake(newSnake) 
    }

    function getRandomNumber(min: number, max: number){
        const minCeiled = Math.ceil(min);
        const maxFloored = Math.ceil(max);
        return Math.floor(Math.random() * (maxFloored - minCeiled) + minCeiled)
    }


    function randomFood(){
        let xVal = getRandomNumber(0,49)
        let yVal = getRandomNumber(0,19)
        return {x: xVal * 15, y: yVal * 15}
    }

    function reset(){
        setGameover(false);
        setScore(0);
        setSnake(startingCoords);
        setMove("");
        setFood(randomFood());

    }

    return (
        <>
            <div>
                {!gameover &&<p style={{textAlign: 'center'}}>{score}</p>}
            </div>
            {gameover && <End score={score} reset={reset}/>}
            <canvas ref={canvasRef} width={canvasWidth} height={canvasHeight} style={{border:"1px solid white"}}></canvas>

        </>
        
    )
}

export default Snake