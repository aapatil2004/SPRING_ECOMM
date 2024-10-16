import React, { useEffect, useState } from "react";
import axios from "axios";

function OrderHistory() {
  const [orders, setOrders] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Fetch order history from the backend
    axios
      .get("http://localhost:8080/api/orders")
      .then((response) => {
        console.log("Order data from API:", response.data); // Debugging log
        setOrders(response.data); // Assuming the data is an array of orders
      })
      .catch((error) => {
        console.error("Error fetching order history", error);
      })
      .finally(() => {
        setIsLoading(false); // Turn off loading once the request is complete
      });
  }, []);

  if (isLoading) {
    return <p>Loading...</p>; // Display a loading indicator
  }

  return (
    <div className="container mt-1">
      <h2>Order History</h2>
      {orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        <ul className="list-group">
          {orders.map((order) => (
            <li key={order.id} className="list-group-item">
              <p>
                <strong>Order ID:</strong> {order.id}
              </p>
              <p>
                <strong>Amount:</strong> {order.amount} {order.currency}
              </p>
              <p>
                <strong>Status:</strong> {order.status}
              </p>
              <p>
                <strong>Date:</strong>{" "}
                {new Date(order.orderDate).toLocaleString()}
              </p>

              <h5>Products:</h5>
              <ul>
                {order.productInfo.map((product) => (
                  <li key={product.id}>
                    <p>
                      <strong>Product Name:</strong> {product.name}
                    </p>
                    <p>
                      <strong>Price:</strong> ${product.price}
                    </p>
                    <p>
                      <strong>Quantity:</strong> {product.quantity}
                    </p>
                  </li>
                ))}
              </ul>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default OrderHistory;
