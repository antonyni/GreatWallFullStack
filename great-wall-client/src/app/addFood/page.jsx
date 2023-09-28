'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useHistory } from 'react-router-dom';

import { useEffect, useState} from 'react';


const Home = () => {
    const [formData, setFormData] = useState({
        name: '',
        section: ''
    });




    

    const handleSubmit = (event) => {
        event.preventDefault();
        foodRequestService.createFoodRequest(formData);
        console.log(formData);
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">Name:</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                />
            </div>

            <div>
                <label htmlFor="section">Section:</label>
                <input
                    type="text"
                    id="section"
                    name="section"
                    value={formData.section}
                    onChange={handleChange}
                    required
                />
            </div>

            <button type="submit">submit</button>
        </form>
    );
}

export default Home;