import React from "react";

import Button from "react-bootstrap/Button";
import ListGroup from 'react-bootstrap/ListGroup';
import AddModal from "./modals/AddModal";
import history from "../history/history";
import CityService from "../services/CityService";

export default class Cities extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            cities: props.cities.sort((a, b) => (a.name > b.name) ? 1 : -1),
            state_name: props.state_name,
            state_id: props.state_id,
            addCityModalOpen: false,
            modalText: ""
        };
        this.addCity = this.addCity.bind(this)
        this.showCity = this.showCity.bind(this)
        this.handleModalOptionSave = this.handleModalOptionSave.bind(this)
        this.handleModalOptionClose = this.handleModalOptionClose.bind(this)
        this.handleModalTextChange = this.handleModalTextChange.bind(this)
        this.fetchNewCity = this.fetchNewCity.bind(this)
        this.handleSuccessfulSave = this.handleSuccessfulSave.bind(this)
    }
    
      addCity() {
        this.setState(() => ({
            addCityModalOpen: true
          }))
      }
    
      showCity(id) {
        const url = "/city/" + id;
        history.push(url)
        window.location.reload()
      }

      handleModalOptionSave(text) {
        this.setState(() => ({
            addCityModalOpen: false
        }))
        this.fetchNewCity(this.state.modalText)
        console.log("Nové město:")
        console.log(this.state.modalText)
        this.setState(() => ({
            modalText: ""
        }))
      }
  
      handleModalOptionClose() {
        this.setState(() => ({
            addCityModalOpen: false,
            modalText: ""
        }))
      }

      handleModalTextChange = (event) => {
        this.setState(() => ({
            modalText: event.target.value
        }))
    };

    fetchNewCity(cityName) {
      CityService.addNewCity(cityName, this.state.state_id, this.state.state_name)
      .then(response => response.json())
      .then(this.handleSuccessfulSave())
      .catch((error) => {
        console.error('Error:', error);
      });
      
      /*
      const newCity = {
            name: cityName,
            state: {
                id: this.state.state_id,
                name: this.state.state_name
            }
          }
        fetch("/api/city", {
            method: 'POST',
            credentials: 'include',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(newCity),
          }).then(response => response.json())
          .then(this.handleSuccessfulSave())
          .catch((error) => {
            console.error('Error:', error);
          });*/
    }

    handleSuccessfulSave() {
        window.location.reload()
    }

    render() {
        return (
            <div className="cities">
                {this.state.cities.length === 0 && <p className="font-weight-lighter">Nejsou přidána žádná města.</p>}
                <ListGroup variant="flush">
                    {(this.state.cities) && this.state.cities.map((city, index) => (
                        <div key={index}>
                        <ListGroup.Item key={index} action onClick={() => this.showCity(city.id)}>📍 {city.name}</ListGroup.Item>
                        </div>
                    ))}
                    <div>
                        <Button variant="outline-dark" size="sm" type="button" onClick={this.addCity} className="add_button">Přidat město</Button>
                    </div>
                </ListGroup>


                <AddModal
                  show={this.state.addCityModalOpen}
                  title={"Přidání města"}
                  message={"Zadejte název města"}
                  placeholder={"Název města"}
                  handleSave={this.handleModalOptionSave} 
                  handleClose={this.handleModalOptionClose}
                  handleTextChange={this.handleModalTextChange}
                  text={this.state.modalText}
                />
            </div>
        )
    }
}