// This file is current not used
// All *DataService.ts files import axios 
// Look into how this file works and possibly convert all *DataService.ts to using this in the future

import axios from 'axios';

export default axios.create({
    baseURL: `${window.location.protocol}//${window.location.host}/api`,
    headers: {
        'Content-type': 'application/json'
    }
});