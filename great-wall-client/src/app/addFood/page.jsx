'use client'
import * as userRequestService from '../../services/UserService';
import * as orderRequestService from '../../services/OrderService';
import * as foodRequestService from '../../services/FoodService';
import * as foodOptionsRequestService from '../../services/FoodOptionsService';
import * as foodChoicesRequestService from '../../services/FoodChoicesService';
import { useRouter } from 'next/navigation'

import { useEffect, useState} from 'react';


const Home = () => {
    const [formData, setFormData] = useState({
        name: '',
        section: ''
    });


    const router = useRouter();




    

    const handleSubmit = async (event) => {
        event.preventDefault();
        
        try{
            await foodRequestService.createFoodRequest(formData)
            router.push('/admin');
        }catch (error){
             console.log(error.response.data);

        }
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