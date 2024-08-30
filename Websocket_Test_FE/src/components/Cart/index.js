import { useState, useEffect } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function Cart() {
  const [cartStatus, setCartStatus] = useState("");
  const [stompClient, setStompClient] = useState(null);
  const [user, setUser] = useState("");

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/cartStatus", (message) => {
        setCartStatus(JSON.parse(message.body).message);
      });
    });
    setStompClient(stompClient);

    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, []);

  const addToCart = () => {
    if (user) {
      stompClient.send("/app/addToCart", {}, JSON.stringify({ user }));
    } else {
      alert("Please enter your name first.");
    }
  };

  return (
    <div>
      <h2>Giỏ hàng</h2>
      <input
        type="text"
        placeholder="Tên của bạn"
        value={user}
        onChange={(e) => setUser(e.target.value)}
      />
      <button onClick={addToCart}>Thêm vào giỏ hàng</button>
      <div>
        <p>{cartStatus}</p>
      </div>
    </div>
  );
}

export default Cart;
