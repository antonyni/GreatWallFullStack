import axios from 'axios';

const FOOD_BASE_API_URL = 'http://localhost:8080/api/v1/FoodRequests';




export function getAllFoodRequests(){
    return axios.get(FOOD_BASE_API_URL);
}

export function createFoodRequest(foodRequest){
    return axios.post(FOOD_BASE_API_URL,foodRequest);
}


export function getFoodById(id){
    return axios.get(`${FOOD_BASE_API_URL}/${id}`);
}

export function updateFoodRequest(id, foodRequest){
    return axios.put(`${FOOD_BASE_API_URL}/${id}`, foodRequest);
}

export function deleteFoodRequest(id){
    return axios.delete(`${FOOD_BASE_API_URL}/${id}`);
}
