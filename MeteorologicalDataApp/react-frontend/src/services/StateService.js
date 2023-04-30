//import axios from 'axios';

//const STATE_API_BASE_URL = 'http://localhost:8080/api/state';

class StateService{

    getStates(){
        //return axios.get(STATE_API_BASE_URL);

        return fetch("/api/states", {
            method: 'GET',
            credentials: 'include',
            headers: {
              Accept: 'application/json'
            }
        })
    }

    addNewState(stateName){
        const newState = {
            name: stateName,
        }
        return fetch("/api/state", {
            method: 'POST',
            credentials: 'include',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(newState),
        })
    }
}

export default new StateService();