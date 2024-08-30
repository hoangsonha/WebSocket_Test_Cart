import React from "react";
import Cart from "./components/Cart";
import Product from "./components/Product";
import { StompSessionProvider } from "react-stomp-hooks";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Mua sắm trực tuyến</h1>
        <StompSessionProvider url={"http://localhost:8080/ws"}>
          <Product />
        </StompSessionProvider>
      </header>
    </div>
  );
}

export default App;
