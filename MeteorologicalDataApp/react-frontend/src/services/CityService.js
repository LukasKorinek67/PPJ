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

    getCity(cityId) {
        const url = "/api/city/" + cityId
        return fetch(url, {
          method: 'GET',
          credentials: 'include',
          headers: {
            Accept: 'application/json'
          }
        })
    }

    getLatestWeatherForCity(cityId) {
        const url = "/api/latest/" + cityId
        return fetch(url, {
            method: 'GET',
            credentials: 'include',
            headers: {
              Accept: 'application/json'
            }
        })
    }

    getAverageWeatherLastDay(cityId) {
        const url = "/api/average/day/" + cityId
        return fetch(url, {
            method: 'GET',
            credentials: 'include',
            headers: {
              Accept: 'application/json'
            }
        })
    }

    getAverageWeatherLastWeek(cityId) {
        const url = "/api/average/week/" + cityId
        return fetch(url, {
            method: 'GET',
            credentials: 'include',
            headers: {
              Accept: 'application/json'
            }
        })
    }

    getAverageWeatherLast14days(cityId) {
        const url = "/api/average/14days/" + cityId
        return fetch(url, {
            method: 'GET',
            credentials: 'include',
            headers: {
              Accept: 'application/json'
            }
        })
    }

}
const cityService = new CityService();
export default cityService;
//export default new CityService();