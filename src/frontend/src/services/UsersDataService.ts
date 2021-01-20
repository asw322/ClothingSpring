import axios from 'axios';

const API_URL_BASE = 'http://localhost:8080'
const CLOTHING_API_URL = `${API_URL_BASE}/api/user`

class UsersDataService {
    // Login based on the user input object
    login(data: any) {
        // return axios.get(`${CLOTHING_API_URL}`, data)
        return axios.get(`${CLOTHING_API_URL}`)
    }
}

export default new UsersDataService();