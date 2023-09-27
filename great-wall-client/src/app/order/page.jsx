'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useEffect, useState} from 'react';


export default function Home() {
    const [userRequests, setUserRequests]= useState([]);
    const [orderRequests, setOrderRequests]= useState([]);
    const [foodRequests, setFoodRequests]= useState([]);
    const [foodOptionsRequests, setFoodOptionsRequests]= useState([]);
    const [foodChoicesRequests, setFoodChoicesRequests]= useState([]);

    return (
      <>
      

      </>
      
    )
  }
  