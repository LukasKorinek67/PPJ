import React from "react";
import { Link } from "react-router-dom";
import history from "../history/history";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Stack from 'react-bootstrap/Stack';
import BootstrapIcons from "../utils/BootstrapIcons";

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
                  <Link to="/" className="text-decoration-none">
                    <div id="main_h" className="text-white">
                    <Stack direction="horizontal" gap={2}>
                            {BootstrapIcons.thermometerSun(60,60)}
                            <h1 id="main_h" className="text-white fw-normal">MeteorologicalDataApp</h1>
                    </Stack>
                    </div>


                    {//<div id="logo"><h1><span className="badge badge-light">MeteorologicalDataApp</span></h1></div>
                    }
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