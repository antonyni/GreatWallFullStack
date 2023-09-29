'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useEffect, useState } from 'react';
import Image from 'next/image';

import CogIcon from "../../images/cog.svg"


export default function Home() {
    const [foodRequests, setFoodRequests] = useState([]);
    const [foodOptionsRequests, setFoodOptionsRequests] = useState([]);
    const [foodChoicesRequests, setFoodChoicesRequests] = useState([]);

    const populate = () => {
        foodRequestService.getAllFoodRequests()
            .then((res) => {
                makeSections(res.data);
            })
    }

    const makeSections = (foodData) => {
        let sectionMap = {};
        for (const foodItem of foodData) {
            const foodSection = foodItem.section;
            if (!sectionMap[foodSection]) {
                sectionMap[foodSection] = [];
            }
            sectionMap[foodSection].push(foodItem);

        }
        const sectionTable = Object.entries(sectionMap);
        sectionTable.sort((a, b) => {
            const numA = a[0].split(".", 1);
            const numB = b[0].split(".", 1);
            return numA - numB;
        });
        setFoodRequests(sectionTable);
    }
    useEffect(() => {
        populate();
    }, [])



    return (
        <>
            {/* <div><a href="http://localhost:3000/addFood">add food</a></div>
        <div><a href="google.com">add side order</a></div> */}
            <div style={{ height: "100vh", width: "100vw", backgroundColor: "#C4D0BA ", display: "flex", alignItems: "center", justifyContent: "center  " }}>
                <div style={{ height: "90vh", width: "90vw", backgroundColor: "#9AD869 ", borderRadius: "1vw", display: "flex", justifyContent: "center" }}>

                    <div style={{ borderRadius:"1vw",height: "18vw", width: "18vw", backgroundColor: "#7fcd41 ",display:'flex',justifyContent:'center',alignItems:"center",flexDirection:'column' }}>
                        
                        <div style={{ height: "12vw", width: "12vw", position: "relative" }}>
                            <Image
                                src={CogIcon}
                                fill={true}
                            />
                        </div>
                        <h1 style={{fontSize:"2.5vw"}}>Settings</h1>

                    </div>



                </div>

            </div>


        </>

    )
}
  /*
{
    foodRequests.map((data) => {
        return(
            <div style={{fontSize:"40px"}} key={data[0]}>
                <h1 > {data[0]}</h1>
                {
                    data[1].map((food)=>{
                        return(
                            <div style={{fontSize:"20px"}} key={food.id}>
                                {
                                    food.name
                                    
                                }
                                <div style={{fontSize:"15px",paddingLeft:"10px"}}>
                                    {food.foodOptionsList.map((side)=>{
                                        return(
                                            <div key={side.id}>
                                                {side.name}
                                            </div>
                                        )
                                    })}
                                </div>

                            </div>
                        )
                    })
                }
            </div >
        )
    })
}
*/