import axios, { AxiosRequestConfig } from 'axios';

const API_URL_BASE = 'http://localhost:8080'
const CLOTHING_API_URL = `${API_URL_BASE}/api/user`

export interface AxiosResponse<T> {
    username: string,
    data: T;
    status: number;
    statusText: string;
    headers: any;
    config: AxiosRequestConfig;
  }

class UsersDataService {
    // Login based on the user input
    login(username: string, password: string) {
        return axios.get(`${CLOTHING_API_URL}`, {
            params: {
                username, 
                password
            }
        })
    }
}

export default new UsersDataService();