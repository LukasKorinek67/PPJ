import React from "react";
import TimestampHandler from "../utils/TimestampHandler";
import BootstrapIcons from "../utils/BootstrapIcons";
import Stack from 'react-bootstrap/Stack';

export default class ActualWeather extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            actualWeather: props.actualWeather
        };
        this.getWeatherIcon = this.getWeatherIcon.bind(this)
    }

    getWeatherIcon(weather) {
        //width = 16;
        //height = 16;
        const width = 70;
        const height = 70;
        if (weather === "Clear") {
            return BootstrapIcons.sun(width, height)
        } else if(weather === "Clouds") {
            return BootstrapIcons.clouds(width, height)
        } else if(weather === "Rain") {
            return BootstrapIcons.rain(width, height)
        } else if(weather === "Snow") {
            return BootstrapIcons.snow(width, height)
        } else if(weather === "Thunderstorm") {
            return BootstrapIcons.thunderstorm(width, height)
        } else if(weather === "Drizzle") {
            return BootstrapIcons.drizzle(width, height)
        } else if(weather === "Mist") {
            return BootstrapIcons.fog(width, height)
        } else if(weather === "Smoke") {
            return BootstrapIcons.wind(width, height)
        } else if(weather === "Haze") {
            return BootstrapIcons.haze(width, height)
        } else if(weather === "Dust") {
            return BootstrapIcons.wind(width, height)
        } else if(weather === "Fog") {
            return BootstrapIcons.fog(width, height)
        } else if(weather === "Sand") {
            return BootstrapIcons.wind(width, height)
        } else if(weather === "Ash") {
            return BootstrapIcons.wind(width, height)
        } else if(weather === "Squall") {
            return BootstrapIcons.rain(width, height)
        } else if(weather === "Tornado") {
            return BootstrapIcons.tornado(width, height)
        } else {
            return BootstrapIcons.clouds(width, height)
        }
    }

    render() {
        return (
            <div className="actual_weather">
                {(!this.state.actualWeather.temperature) && <p class="fw-lighter">Data nebyla stažena!</p>}
                
                {(this.state.actualWeather.cloudiness!== undefined && this.state.actualWeather.feelsLikeTemperature!== undefined && this.state.actualWeather.humidity!== undefined
                 && this.state.actualWeather.pressure!== undefined && this.state.actualWeather.temperature!== undefined && this.state.actualWeather.timestamp!== undefined 
                 && this.state.actualWeather.visibility!== undefined && this.state.actualWeather.weather!== undefined && this.state.actualWeather.weatherDescription!== undefined 
                 && this.state.actualWeather.windSpeed!== undefined) && 
                    <div className="actual_weather_data">
                        <div class="border-bottom border-black p-3">
                        <Stack direction="horizontal" gap={3} justifyContent="center">
                            {this.getWeatherIcon(this.state.actualWeather.weather)}
                            <p className="temperature fs-1">{this.state.actualWeather.temperature}°C</p>
                        </Stack>
                        </div>
                        <p class="fw-lighter"> </p>
                        <p class="fw-lighter text-capitalize">Počasí: {this.state.actualWeather.weatherDescription}</p>
                        <p class="fw-lighter">Pocitová teplota: {this.state.actualWeather.feelsLikeTemperature}°C</p>
                        <p class="fw-lighter">Oblačnost: {this.state.actualWeather.cloudiness}%</p>
                        <p class="fw-lighter">Rychlost větru: {this.state.actualWeather.windSpeed}m/s</p>
                        <p class="fw-lighter">Tlak: {this.state.actualWeather.pressure}hPa</p>
                        <p class="fw-lighter">Vlhkost vzduchu: {this.state.actualWeather.humidity}%</p>
                        <p class="fw-lighter">Viditelnost: {this.state.actualWeather.visibility}m</p>
                        <p class="fw-lighter">Zaznamenáno: {TimestampHandler.timestampToDate(this.state.actualWeather.timestamp)}</p>
                    </div>}
            </div>
        )
    }
}