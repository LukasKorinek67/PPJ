import React from "react";
import Header from "../Header";
import States from "../States";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";
//import history from "../../history/history";
//import Button from "react-bootstrap/Button";
//import Card from "react-bootstrap/Card";
//import Container from "react-bootstrap/Container";


export default class MainPage extends React.Component {

  /*
  <ListGroup variant="flush">
                  {(this.state.states) && this.state.states.map((state, index) => (
                        <div>
                          <ListGroup.Item key={index} action variant="light">📍 {state.name}</ListGroup.Item>
                          <ListGroup variant="flush">
                            {(state.cities) && state.cities.map((city, index) => (
                              <div>
                                <ListGroup.Item key={index} action variant="info">{city.name}</ListGroup.Item>
                              </div>
                            ))}
                          </ListGroup>
                        </div>
                  ))}
                </ListGroup>
  */

  render() {
    return (
      <div>
        <Header />
        <div className="main_content">
            <Card className="pageCard shadow-lg p-3 mb-5 bg-white rounded">
              <Container>
                <h1 id="main_title">Státy</h1>
                <States />
                <div className="card_bottom"></div>
              </Container>
            </Card>
        </div>
     </div>
    )
  }
}