import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import Footer from "./components/Footer";
import MainPage from "./components/pages/MainPage";
import CityPage from "./components/pages/CityPage";
import NotFoundPage from "./components/pages/NotFoundPage";
import history from "./history/history";

export default class App extends React.Component {

  render() {
      return (  
          <Router>
              <div>
                  <Switch>
                    <Route path="/" component={MainPage} exact={true} />
                    <Route path="/city/:name" component={CityPage} exact={true} />
                    <Route component={NotFoundPage} />
                  </Switch>
                  <Footer />
              </div>
          </Router>
      );
  }
}
