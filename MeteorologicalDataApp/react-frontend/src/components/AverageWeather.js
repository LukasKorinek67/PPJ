import React from "react";

export default class AverageWeather extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            averageWeather: props.averageWeather
        };
    }

    render() {
        return (
            <div className="average_weather">
                {(!this.state.averageWeather.temperature) && <p className="fw-lighter">Data nebyla stažena!</p>}
                
                {(this.state.averageWeather.temperature!== undefined && this.state.averageWeather.feelsLikeTemperature!== undefined && this.state.averageWeather.pressure!== undefined
                 && this.state.averageWeather.humidity!== undefined && this.state.averageWeather.visibility!== undefined && this.state.averageWeather.windSpeed!== undefined 
                 && this.state.averageWeather.cloudiness!== undefined) && 
                    <div className="average_weather_data">
                        <p className="fw-lighter">Teplota: {(this.state.averageWeather.temperature).toFixed(2)}°C</p>
                        <p className="fw-lighter">Pocitová teplota: {(this.state.averageWeather.feelsLikeTemperature).toFixed(2)}°C</p>
                        <p className="fw-lighter">Oblačnost: {this.state.averageWeather.cloudiness}%</p>
                        <p className="fw-lighter">Rychlost větru: {(this.state.averageWeather.windSpeed).toFixed(2)}m/s</p>
                        <p className="fw-lighter">Tlak: {this.state.averageWeather.pressure}hPa</p>
                        <p className="fw-lighter">Vlhkost vzduchu: {this.state.averageWeather.humidity}%</p>
                        <p className="fw-lighter">Viditelnost: {this.state.averageWeather.visibility}m</p>
                    </div>}
            </div>
        )
    }
}