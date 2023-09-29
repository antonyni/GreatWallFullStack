'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useEffect, useState } from 'react';
import Image from 'next/image';

import CogIcon from "../../images/cog.svg"
import PeopleIcon from "../../images/image 10.svg"

import Frame1Icon from "../../images/image 12.svg"
import Frame2Icon from "../../images/image 13.svg"
import graphIcon from "../../images/graph.png"
import personIcon from "../../images/image 1.svg"



export default function Home() {
    const [foodRequests, setFoodRequests] = useState([]);
    const [foodOptionsRequests, setFoodOptionsRequests] = useState([]);
    const [foodChoicesRequests, setFoodChoicesRequests] = useState([]);
    const [orderRequests, setOrderRequests] = useState([]);

    const populate = () => {
        foodRequestService.getAllFoodRequests()
            .then((res) => {
                makeSections(res.data);
            })
        orderRequestService.getAllOrderRequests()
            .then((res) => {
                setOrderRequests(res.data);
                console.log(res.data);
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
                <div style={{ height: "90vh", width: "90vw", backgroundColor: "#9AD869 ", borderRadius: "1vw", display: "flex", justifyContent: "center", alignItems: "center" }}>
                    <div style={{ marginTop: "0vw", height: "100%", width: "100%", display: "flex", justifyContent: "center" }}>

                        <div style={{ height: "100%", width: "60%", display: "flex", justifyContent: "center", flexDirection: "column", alignItems: "center" }}>
                            <div style={{ height: "20vw", width: "50vw", position: "relative", backgroundColor: "#7fcd41 ", marginBottom: "5vh", borderRadius: "1vw" }}>
                                <h1 style={{ marginLeft: "2vw", marginTop: "3vh", fontSize: "2vw" }}>Current Orders</h1>
                                <div style={{ height: "15vw", width: "50vw", display: "flex", flexWrap: "wrap" }}>
                                    {
                                        orderRequests.map((order) => {
                                            return (
                                                <div style={{ marginLeft: "2vw", height: "7vw", width: "20vw", backgroundColor: "#9AD869", display: "flex" }} key={order.id}>
                                                    <div style={{ height: "5vw", width: "5vw", position: "relative" }}>
                                                        <Image
                                                            src={personIcon}
                                                            fill={true}
                                                        />
                                                    </div>
                                                    <div style={{display:"flex",flexDirection:"column"}}>
                                                    <div>Total: ${order.total}</div>
                                                    <div>Status: {order.status}</div>
                                                    <div>Id : {order.id}</div>
                                                    </div>
                                                   

                                                </div>
                                            )
                                        })
                                    }


                                </div>

                            </div>
                            <div style={{ height: "20vw", width: "50vw", position: "relative" }}>
                                <Image
                                    src={graphIcon}
                                    fill={true}
                                />
                            </div>

                        </div>
                        <div style={{ height: "100%", width: "40%", display: "flex", justifyContent: "space between", flexDirection: "column" }}>
                            <div style={{ display: "flex" }}>
                                <div style={{ marginTop: "2vw", marginRight: "2vw", borderRadius: "1vw", height: "18vw", width: "18vw", backgroundColor: "#7fcd41 ", display: 'flex', justifyContent: 'center', alignItems: "center", flexDirection: 'column' }}>

                                    <div style={{ height: "10vw", width: "10vw", position: "relative" }}>
                                        <Image
                                            src={PeopleIcon}
                                            fill={true}
                                        />
                                    </div>
                                    <h1 style={{ fontSize: "2.5vw" }}>Users</h1>

                                </div>
                                <div style={{ marginTop: "2vw", marginRight: "2vw", borderRadius: "1vw", height: "18vw", width: "18vw", backgroundColor: "#7fcd41 ", display: 'flex', justifyContent: 'center', alignItems: "center", flexDirection: 'column' }}>

                                    <div style={{ height: "10vw", width: "10vw", position: "relative" }}>
                                        <Image
                                            src={CogIcon}
                                            fill={true}
                                        />
                                    </div>
                                    <h1 style={{ fontSize: "2.5vw" }}>Settings</h1>

                                </div>
                            </div>
                            <div style={{ display: "flex" }}>
                                <div style={{ marginTop: "2vw", marginRight: "2vw", borderRadius: "1vw", height: "22vw", width: "18vw", backgroundColor: "#7fcd41 ", display: 'flex', justifyContent: 'center', alignItems: "center", flexDirection: 'column' }}>

                                    <div style={{ height: "10vw", width: "10vw", position: "relative", marginBottom: "2vw" }}>
                                        <Image
                                            src={Frame1Icon}
                                            fill={true}
                                        />
                                    </div>
                                    <h1 style={{ fontSize: "2.5vw" }}>Edit Menu</h1>

                                </div>
                                <div style={{ marginTop: "2vw", marginRight: "2vw", borderRadius: "1vw", height: "22vw", width: "18vw", backgroundColor: "#7fcd41 ", display: 'flex', justifyContent: 'center', alignItems: "center", flexDirection: 'column' }}>

                                    <div style={{ height: "10vw", width: "10vw", position: "relative", marginBottom: "2vw" }}>
                                        <Image
                                            src={Frame2Icon}
                                            fill={true}
                                        />
                                    </div>
                                    <h1 style={{ fontSize: "2.5vw" }}>Edit Hours</h1>

                                </div>
                            </div>



                        </div>



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