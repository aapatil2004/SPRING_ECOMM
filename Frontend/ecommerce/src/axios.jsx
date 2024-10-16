//Axios is a popular, promise-based HTTP client for making requests from the browser (frontend) to external APIs or backend servers. In the context of React, Axios is frequently used to perform asynchronous operations such as fetching data, sending data to the server, or handling CRUD (Create, Read, Update, Delete) operations.
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api",
});
delete API.defaults.headers.common["Authorization"];
export default API;
