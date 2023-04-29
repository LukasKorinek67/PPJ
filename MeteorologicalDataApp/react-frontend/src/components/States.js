import React from "react";

import Button from "react-bootstrap/Button";
import ListGroup from 'react-bootstrap/ListGroup';
import Accordion from 'react-bootstrap/Accordion';
import AddModal from "./modals/AddModal";
import Cities from "./Cities";

export default class States extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            states: [],
            addStateModalOpen: false,
            modalText: ""
        };
        this.addState = this.addState.bind(this)
        this.handleModalOptionSave = this.handleModalOptionSave.bind(this)
        this.handleModalOptionClose = this.handleModalOptionClose.bind(this)
        this.handleModalTextChange = this.handleModalTextChange.bind(this)
        this.fetchNewState = this.fetchNewState.bind(this)
        this.handleSuccessfulSave = this.handleSuccessfulSave.bind(this)
    }
    
    
    addState() {
        this.setState(() => ({
            addStateModalOpen: true
          }))
    }
    
    componentDidMount() {
        fetch("/api/state", {
          method: 'GET',
          credentials: 'include',
          headers: {
            Accept: 'application/json'
          }
        }).then(response => response.json())
        .then(data => {
          if(data){
            data = data.sort((a, b) => (a.name > b.name) ? 1 : -1)
            this.setState((prevState) => ({
              states: data,
            }))
          }
        }).catch((error) => {
          console.error('Error: ', error);
        });
      }

    handleModalOptionSave(text) {
        this.setState(() => ({
            addStateModalOpen: false
        }))
        this.fetchNewState(this.state.modalText)
        console.log("Nový stát:")
        console.log(this.state.modalText)
        this.setState(() => ({
            modalText: ""
        }))
      }
  
    handleModalOptionClose() {
        this.setState(() => ({
            addStateModalOpen: false,
            modalText: ""
        }))
    }

    handleModalTextChange = (event) => {
        this.setState(() => ({
            modalText: event.target.value
        }))
        };

    fetchNewState(stateName) {
        const newState = {
            name: stateName,
          }
        fetch("/api/state", {
            method: 'POST',
            credentials: 'include',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(newState),
          }).then(response => response.json())
          .then(this.handleSuccessfulSave())
          .catch((error) => {
            console.error('Error:', error);
          });
    }

    handleSuccessfulSave() {
        window.location.reload()
    }



    render() {
        return (
              <div className="states">
                {this.state.states.length === 0 && <p className="font-weight-lighter">Nejsou přidány žádné státy.</p>}
                <Accordion>
                {(this.state.states) && this.state.states.map((state, index) => (
                    <div key={index}>
                        <Accordion.Item eventKey={index} key={index}>
                            <Accordion.Header>🌎 {state.name}</Accordion.Header>
                            <Accordion.Body>
                            <Cities cities={state.cities} state_name={state.name} state_id={state.id}/>
                            </Accordion.Body>
                            </Accordion.Item>
                        </div>
                ))}
                </Accordion>

                <Button variant="outline-dark" size="sm" type="button" onClick={this.addState} className="add_button">Přidat stát</Button>

                <AddModal
                  show={this.state.addStateModalOpen}
                  title={"Přidání státu"}
                  message={"Zadejte název státu"}
                  placeholder={"Název státu"}
                  handleSave={this.handleModalOptionSave} 
                  handleClose={this.handleModalOptionClose}
                  handleTextChange={this.handleModalTextChange}
                  text={this.state.modalText}
                />
            </div>
        )
    }
}