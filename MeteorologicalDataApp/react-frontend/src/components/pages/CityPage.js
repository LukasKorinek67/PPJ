import React from "react";
import Header from "../Header";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";
import history from "../../history/history";
import Button from "react-bootstrap/Button";
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import CityService from "../../services/CityService";
import ActualWeather from "../ActualWeather";
import AverageWeather from "../AverageWeather";
import BootstrapIcons from "../../utils/BootstrapIcons";
import Stack from 'react-bootstrap/Stack';


export default class CityPage extends React.Component {


    constructor(props){
        super(props)
        this.state = {
            city: {
                id: 0,
                name: ""
            },
            actualWeather: {},
            averageLastDay: {},
            averageLastWeek: {},
            averageLast14Days: {},
            cityLoaded: false,
            actualWeatherLoaded: false,
            averageLastDayLoaded: false,
            averageLastWeekLoaded: false,
            averageLast14DaysLoaded: false
        }
        this.getCity = this.getCity.bind(this)
        this.getLatestWeather = this.getLatestWeather.bind(this)
        this.getAverageWeathers = this.getAverageWeathers.bind(this)
        this.getAverageLastDay = this.getAverageLastDay.bind(this)
        this.getAverageLastWeek = this.getAverageLastWeek.bind(this)
        this.getAverageLast14Days = this.getAverageLast14Days.bind(this)
    }


    componentDidMount() {
      this.getCity()
      this.getLatestWeather()
      this.getAverageWeathers()
    }

    getCity() {
      const cityId = window.location.pathname.split("/")[2];
      CityService.getCity(cityId)
      .then(response => response.json())
      .then(data => {
        if(data.id && data.name){
              this.setState((prevState) => ({
                  city: data,
                  cityLoaded: true
              }))
          } else {
              this.setState((prevState) => ({
                city: {
                  id: 0,
                  name: "not_exists"
                },
                cityLoaded: false
              }))
          }
      }).catch((error) => {
          this.setState((prevState) => ({
            city: {
              id: 0,
              name: "not_exists"
            },
            cityLoaded: false
            }))
          console.error('Error: ', error);
      });
    }

    getLatestWeather() {
      const cityId = window.location.pathname.split("/")[2];
      CityService.getLatestWeatherForCity(cityId)
      .then(response => response.json())
      .then(data => {
        if(data.cloudiness !== undefined && data.feelsLikeTemperature !== undefined && data.humidity !== undefined && data.pressure !== undefined 
          && data.temperature !== undefined && data.timestamp !== undefined && data.visibility !== undefined && data.weather !== undefined 
          && data.weatherDescription !== undefined && data.windSpeed !== undefined){
          this.setState((prevState) => ({
            actualWeather: data,
            actualWeatherLoaded: true
          }))
        } else {
          this.setState((prevState) => ({
            actualWeatherLoaded: false
          }))
        }
      }).catch((error) => {
        this.setState((prevState) => ({
          actualWeatherLoaded: false
        }))
        console.error('Error: ', error);
      });
    }

    getAverageWeathers() {
      const cityId = window.location.pathname.split("/")[2];
      this.getAverageLastDay(cityId)
      this.getAverageLastWeek(cityId)
      this.getAverageLast14Days(cityId)
    }

    getAverageLastDay(cityId) {
      CityService.getAverageWeatherLastDay(cityId)
      .then(response => response.json())
      .then(data => {  
        if(data.city !== undefined && data.temperature !== undefined && data.feelsLikeTemperature !== undefined 
          && data.pressure !== undefined && data.humidity !== undefined){ 
          this.setState((prevState) => ({
            averageLastDay: data,
            averageLastDayLoaded: true
          }))
        } else {
          this.setState((prevState) => ({
            averageLastDayLoaded: false
          }))
        }
      }).catch((error) => {
        this.setState((prevState) => ({
          averageLastDayLoaded: false
        }))
        console.error('Error: ', error);
      });
    }

    getAverageLastWeek(cityId) {
      CityService.getAverageWeatherLastWeek(cityId)
      .then(response => response.json())
      .then(data => {
        if(data.city !== undefined && data.temperature !== undefined && data.feelsLikeTemperature !== undefined 
          && data.pressure !== undefined && data.humidity !== undefined){ 
          this.setState((prevState) => ({
            averageLastWeek: data,
            averageLastWeekLoaded: true
          }))
        } else { 
          this.setState((prevState) => ({
            averageLastWeekLoaded: false
          }))
        }
      }).catch((error) => {
        this.setState((prevState) => ({
          averageLastWeekLoaded: false
        }))
        console.error('Error: ', error);
      });
    }

    getAverageLast14Days(cityId) {
      CityService.getAverageWeatherLast14days(cityId)
      .then(response => response.json())
      .then(data => {
        if(data.city !== undefined && data.temperature !== undefined && data.feelsLikeTemperature !== undefined 
          && data.pressure !== undefined && data.humidity !== undefined){  
          this.setState((prevState) => ({
            averageLast14Days: data,
            averageLast14DaysLoaded: true
          }))
        } else {
          this.setState((prevState) => ({
            averageLast14DaysLoaded: false
          }))
        }
      }).catch((error) => {
        this.setState((prevState) => ({
          averageLast14DaysLoaded: false
        }))
        console.error('Error: ', error);
      });
    }

    goBack() {
      history.push("/")
      window.location.reload()
    }

    render() {
        return (
          <div>
            <Header />
            <div className="main_content">
                <Card className="pageCard shadow-lg p-3 mb-5 bg-white rounded">
                    <Container>
                        <div className="content">
                        {(!this.state.cityLoaded && (this.state.city.name === "not_exists")) && 
                          <h2 id="page404_title" className="text-sm-center text-md-left">Město se zadaným ID neexistuje</h2>}
                        {this.state.cityLoaded && 
                          <h1 id="main_title">
                            <Stack direction="horizontal" gap={3}>
                                {BootstrapIcons.buildings(50,50)}
                                {this.state.city.name}
                            </Stack>
                          </h1>}
                        {(this.state.cityLoaded && this.state.actualWeatherLoaded && this.state.averageLastDayLoaded && this.state.averageLastWeekLoaded && this.state.averageLast14Days) &&
                        
                        <Tabs
                          defaultActiveKey="actual"
                          id="uncontrolled-tab-example"
                          className="mb-3"
                        >
                          <Tab eventKey="actual" title={<span className="text-dark">Aktuální počasí</span>}>
                            <ActualWeather actualWeather={this.state.actualWeather}/>
                          </Tab>
                          <Tab eventKey="last_day_average" title={<span className="text-dark">Průměr za poslední den</span>}>
                            <AverageWeather averageWeather={this.state.averageLastDay}/>
                          </Tab>
                          <Tab eventKey="last_week_average" title={<span className="text-dark">Průměr za poslední týden</span>}>
                            <AverageWeather averageWeather={this.state.averageLastWeek}/>
                          </Tab>
                          <Tab eventKey="last_14days_average" title={<span className="text-dark">Průměr za posledních 14 dní</span>}>
                            <AverageWeather averageWeather={this.state.averageLast14Days}/>
                          </Tab>
                        </Tabs>
                        }
                        {(this.state.cityLoaded && (!this.state.actualWeatherLoaded || !this.state.averageLastDayLoaded || !this.state.averageLastWeekLoaded || !this.state.averageLast14Days)) &&
                          <p className="fw-lighter">Data nejsou k dispozici nebo ještě nebyla stažena!</p>
                        }
                        <Button variant="outline-dark" size="sm" type="button" onClick={this.goBack}>Zpět</Button>
                    <div className="card_bottom"></div>
                    </div>
                  </Container>
                </Card>
            </div>
         </div>
        )
      }
    }