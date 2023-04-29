import React from "react";
import Header from "../Header";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";
import { useParams } from "react-router-dom";
//import history from "../../history/history";
//import Button from "react-bootstrap/Button";
//import Card from "react-bootstrap/Card";
//import Container from "react-bootstrap/Container";


export default class CityPage extends React.Component {


    constructor(props){
        super(props)
        this.state = {
            city: {
                id: 0,
                name: ""
            },
            loaded: false
        }
    }


    componentDidMount() {
        const cityId = window.location.pathname.split("/")[2];
        const url = "/api/city/" + cityId
        fetch(url, {
          method: 'GET',
          credentials: 'include',
          headers: {
            Accept: 'application/json'
          }
        }).then(response => response.json())
        .then(data => {
            if(data.id && data.name){
                this.setState((prevState) => ({
                    city: data,
                    loaded: true
                }))
            } else {
                this.setState((prevState) => ({
                    loaded: false
                }))
            }
        }).catch((error) => {
            this.setState((prevState) => ({
                loaded: false
              }))
            console.error('Error: ', error);
        });
      }

    render() {
        const cityName = window.location.pathname.split("/")[2];
        
        return (
          <div>
            <Header />
            <div className="main_content">
                <Card className="pageCard shadow-lg p-3 mb-5 bg-white rounded">
                    <Container>
                        {!this.state.loaded && <h2 id="page404_title" className="text-sm-center text-md-left">Město se zadaným ID neexistuje</h2>}
                        {this.state.loaded && <h1 id="main_title">📍 {this.state.city.name}</h1>}
                    <div className="card_bottom"></div>
                  </Container>
                </Card>
            </div>
         </div>
        )
      }
    }