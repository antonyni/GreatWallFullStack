import axios from 'axios';

const FOOD_OPTIONS_BASE_API_URL = 'http://localhost:8080/api/v1/FoodOptionsRequests';




export function getAllFoodOptionsRequests(){
    return axios.get(FOOD_OPTIONS_BASE_API_URL);
}

export function createFoodOptionsRequest(foodOptionsRequest){
    return axios.post(FOOD_OPTIONS_BASE_API_URL,foodOptionsRequest);
}


export function getFoodOptionsById(id){
    return axios.get(`${FOOD_OPTIONS_BASE_API_URL}/${id}`);
}

export function updateFoodOptionsRequest(id, foodOptionsRequest){
    return axios.put(`${FOOD_OPTIONS_BASE_API_URL}/${id}`, foodOptionsRequest);
}

export function deleteFoodOptionsRequest(id){
    return axios.delete(`${FOOD_OPTIONS_BASE_API_URL}/${id}`);
}
