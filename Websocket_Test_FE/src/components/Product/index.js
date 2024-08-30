import { useState, useEffect } from "react";
import { useStompClient, useSubscription } from "react-stomp-hooks";

function Product() {
  const [products, setProducts] = useState([]);

  const stompClient = useStompClient();

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then((response) => {
        return response.json();
      })
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  // thông điệp phản hồi sẽ được gửi tới tất cả các client đang lắng nghe (subscribe) kênh /topic/productStatus.

  useSubscription("/topic/productStatus", (message) => {
    const updatedProduct = JSON.parse(message.body);
    setProducts([...updatedProduct.body.data]);
  });

  const addToCart = (id) => {
    if (stompClient) {
      stompClient.publish({ destination: "/app/add", body: id });
    }
  };

  return (
    <div>
      {products.map((product) => (
        <div key={product.id}>
          <h3>{product.name}</h3>
          <p>Price: ${product.price}</p>
          <button
            onClick={() => addToCart(product.id)}
            disabled={!product.status}
          >
            {product.status ? "Add to Cart" : "Out of Stock"}
          </button>
        </div>
      ))}
    </div>
  );
}

export default Product;
