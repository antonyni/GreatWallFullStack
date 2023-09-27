import axios from 'axios';

const USER_BASE_API_URL = 'http://localhost:8080/api/v1/UserRequests';




export function getAllUserRequests(){
    return axios.get(USER_BASE_API_URL);
}

export function createUserRequest(userRequest){
    return axios.post(USER_BASE_API_URL,userRequest);
}


export function getUserById(id){
    return axios.get(`${USER_BASE_API_URL}/${id}`);
}

export function updateUserRequest(id, userRequest){
    return axios.put(`${USER_BASE_API_URL}/${id}`, userRequest);
}

export function deleteUserRequest(id){
    return axios.delete(`${USER_BASE_API_URL}/${id}`);
}
