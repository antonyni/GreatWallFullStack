import axios from 'axios';

const ORDER_BASE_API_URL = 'http://localhost:8080/api/v1/OrderRequests';




export function getAllOrderRequests(){
    return axios.get(ORDER_BASE_API_URL);
}

export function createOrderRequest(orderRequest){
    return axios.post(ORDER_BASE_API_URL,orderRequest);
}

export function getOrderById(id){
    return axios.get(`${ORDER_BASE_API_URL}/${id}`);
}

export function updateOrderRequest(id, orderRequest){
    return axios.put(`${ORDER_BASE_API_URL}/${id}`, orderRequest);
}

export function deleteOrderRequest(id){
    return axios.delete(`${ORDER_BASE_API_URL}/${id}`);
}
