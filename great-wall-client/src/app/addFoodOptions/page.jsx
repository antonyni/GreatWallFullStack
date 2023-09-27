'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useEffect, useState} from 'react';


export default function Home() {
    const [foodRequests, setFoodRequests]= useState([]);
    const [foodOptionsRequests, setFoodOptionsRequests]= useState([]);
    const [foodChoicesRequests, setFoodChoicesRequests]= useState([]);

    const populate = () => {
        foodRequestService.getAllFoodRequests()
        .then((res) => {
            setFoodRequests(res.data);
        })
    }
    useEffect(()=> {
        populate();
     }, [])



    return (
      <>      
        <div><a href="http://localhost:3000/addFood">add food</a></div>
        <div><a href="http://localhost:3000/addFoodOptions">add side order</a></div>


      </>
      
    )
  }
  