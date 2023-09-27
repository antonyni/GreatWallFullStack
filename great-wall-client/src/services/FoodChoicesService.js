import axios from 'axios';

const FOOD_CHOICES_BASE_API_URL = 'http://localhost:8080/api/v1/FoodChoicesRequests';




export function getAllFoodChoicesRequests(){
    return axios.get(FOOD_CHOICES_BASE_API_URL);
}

export function createFoodChoicesRequest(foodChoicesRequest){
    return axios.post(FOOD_CHOICES_BASE_API_URL,foodChoicesRequest);
}


export function getFoodChoicesById(id){
    return axios.get(`${FOOD_CHOICES_BASE_API_URL}/${id}`);
}

export function updateFoodChoicesRequest(id, foodChoicesRequest){
    return axios.put(`${FOOD_CHOICES_BASE_API_URL}/${id}`, foodChoicesRequest);
}

export function deleteFoodChoicesRequest(id){
    return axios.delete(`${FOOD_CHOICES_BASE_API_URL}/${id}`);
}
