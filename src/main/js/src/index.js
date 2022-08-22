("use strict");

const React = require("react");
const ReactDOM = require("react-dom/client");
const axios = require("axios");
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById("react"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
