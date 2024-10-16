import axios from "../axios";
import { useState, useEffect, createContext } from "react";

const AppContext = createContext({
  data: [],
  isError: "",
  cart: [],
  addToCart: (product) => {},
  removeFromCart: (productId) => {},
  refreshData: () => {},
  updateStockQuantity: (productId, newQuantity) => {},
});

export const AppProvider = ({ children }) => {
  const [data, setData] = useState([]);
  const [isError, setIsError] = useState("");
  const [cart, setCart] = useState(
    JSON.parse(localStorage.getItem("cart")) || []
  );

  const addToCart = (product) => {
    // Check if the product stock is 0
    if (product.stockQuantity === 0) {
      alert("This product is out of stock.");
      return; // Exit the function immediately if out of stock
    }

    const existingProductIndex = cart.findIndex(
      (item) => item.id === product.id
    );

    // If the product is already in the cart
    if (existingProductIndex !== -1) {
      const existingProduct = cart[existingProductIndex];

      // Check if the quantity in the cart is equal to or greater than stock
      if (existingProduct.quantity >= product.stockQuantity) {
        alert("Cannot add more, stock limit reached.");
        return; // Exit the function if stock limit is reached
      }

      // Otherwise, increment the quantity
      const updatedCart = cart.map((item, index) =>
        index === existingProductIndex
          ? { ...item, quantity: item.quantity + 1 }
          : item
      );
      setCart(updatedCart);
      localStorage.setItem("cart", JSON.stringify(updatedCart));
      alert("Product added successfully.");
      return; // Exit after successfully adding the product
    }

    // If the product is not already in the cart, add it with quantity 1
    const updatedCart = [...cart, { ...product, quantity: 1 }];
    setCart(updatedCart);
    localStorage.setItem("cart", JSON.stringify(updatedCart));
    alert("Product added successfully.");
  };

  const removeFromCart = (productId) => {
    const updatedCart = cart
      .map((item) => {
        if (item.id === productId) {
          // Decrease quantity or remove if quantity is 1
          if (item.quantity > 1) {
            return { ...item, quantity: item.quantity - 1 };
          }
          return null; // Mark item for removal if quantity is 1
        }
        return item;
      })
      .filter((item) => item !== null); // Remove null values

    setCart(updatedCart);
    localStorage.setItem("cart", JSON.stringify(updatedCart));
    console.log("Updated Cart:", updatedCart);
  };

  const refreshData = async () => {
    try {
      const response = await axios.get("/products");
      setData(response.data);
    } catch (error) {
      setIsError(error.message);
    }
  };

  const clearCart = () => {
    setCart([]);
  };

  useEffect(() => {
    refreshData();
  }, []);

  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(cart));
  }, [cart]);

  return (
    <AppContext.Provider
      value={{
        data,
        isError,
        cart,
        addToCart,
        removeFromCart,
        refreshData,
        clearCart,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export default AppContext;
