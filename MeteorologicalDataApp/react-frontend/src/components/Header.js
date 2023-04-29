import React from "react";
import { Link } from "react-router-dom";
import history from "../history/history";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";

export default class Header extends React.Component {

  updateProfile() {
    history.push("/updateProfile");
  }

  render() {
    return (
      <header>
          <Container fluid>
            <Row xs={1} md={2}>
              <Col>
                <Container>
                <div className="text-sm-center text-md-left">
                  <Link to="/">
                    <div id="logo"><h1><span className="badge badge-light">MeteorologicalDataApp</span></h1></div>
                  </Link>
                  </div>
                </Container>
              </Col>
              <Col>
                <Container>
                  <div className="text-sm-center text-md-right">
                  </div>
                </Container>
              </Col>
            </Row>
          </Container>
        </header>
    );
  }
};