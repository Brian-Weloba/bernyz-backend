// import React from 'react';
// import ReactDOM from 'react-dom';
("use strict");

const React = require("react");
const ReactDOM = require("react-dom");
const axios = require("axios");

// const logo = require("./logo.svg");
// import './App.css';
// import { render } from '@testing-library/react';

class App extends React.Component {
  render(){
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
  }
}


// import App from "./App";
// import "./index.css";
// const client = require("./client");

// class App extends React.Component {
//   constructor(props) {
//     super(props);
//     this.state = { products: [] };
//   }

//   componentDidMount() {
//     axios.get("/v1/products/").then((response) => {
//       this.setState({ products: response.data });
//       console.log("products:: ", this.state.products);
//     });
//   }

//   render() {
//     return <ProductList products={this.state.products} />;
//   }
// }

// class ProductList extends React.Component {
//   render() {
//     const products = this.props.products.map((product) => (
//       <Product key={product.name} product={product} />
//     ));
//     return (
//       <table>
//         <tbody>
//           <tr>
//             <th>Name</th>
//             <th>Description</th>
//             <th>type</th>
//           </tr>
//           {products}
//         </tbody>
//       </table>
//     );
//   }
// }

// class Product extends React.Component {
//   render() {
//     return (
//       <tr>
//         <td>{this.props.product.name}</td>
//         <td>{this.props.product.description}</td>
//         <td>{this.props.product.type}</td>
//       </tr>
//     );
//   }
// }

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("react")
);

// import "./index.css";
// import App from "./App";
// // import reportWebVitals from './reportWebVitals';
// /* Importing the react and react-dom libraries. */

// // var React = require('react');
// // var ReactDOM = require('react-dom');
// // var ReactRouter = require('react-router');

// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
