//import axios from 'axios';

//const STATE_API_BASE_URL = 'http://localhost:8080/api/city';

class CityService{

    addNewCity(cityName, stateID, stateName){
        const newCity = {
            name: cityName,
            state: {
                id: stateID,
                name: stateName
            }
          }
        return fetch("/api/city", {
            method: 'POST',
            credentials: 'include',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(newCity),
        })
    }
}

export default new CityService();