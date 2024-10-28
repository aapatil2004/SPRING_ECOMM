import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function SignInForm() {
  const [state, setState] = React.useState({
    email: "",
    password: "",
  });

  const handleChange = (evt) => {
    const value = evt.target.value;
    setState({
      ...state,
      [evt.target.name]: value,
    });
  };

  const handleOnSubmit = async (evt) => {
    evt.preventDefault();

    const { email, password } = state;

    // Send login request to the server
    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        email,
        password,
      });

      if (response.status === 200) {
        alert("Login successful!");
        // Optionally, clear the form or redirect user
        navigate("/home");
        setState({ email: "", password: "" });
      }

      if (response.ok) {
        const data = await response.json();
        alert(data.message || "Login successful!");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Either Password or email is wrong.");
    }

    // Clear the form fields
    setState({
      email: "",
      password: "",
    });
  };

  return (
    <div className="form-container sign-in-container">
      <form onSubmit={handleOnSubmit}>
        <h1>Sign in</h1>
        <div className="social-container">
          <a href="#" className="social">
            <i className="fab fa-facebook-f" />
          </a>
          <a href="#" className="social">
            <i className="fab fa-google-plus-g" />
          </a>
          <a href="#" className="social">
            <i className="fab fa-linkedin-in" />
          </a>
        </div>
        <span>or use your account</span>
        <input
          type="email"
          placeholder="Email"
          name="email"
          value={state.email}
          onChange={handleChange}
          required // Ensure this field is required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={state.password}
          onChange={handleChange}
          required // Ensure this field is required
        />
        <a href="#">Forgot your password?</a>
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
}

export default SignInForm;
