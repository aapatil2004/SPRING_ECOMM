import React from "react";
import axios from "axios"; // Import axios

function SignUpForm() {
  const [state, setState] = React.useState({
    username: "",
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

    const { username, email, password } = state;

    // Send POST request to /login
    try {
      const response = await axios.post("http://localhost:8080/api/SignUp", {
        username,
        email,
        password,
      });

      if (response.status === 200) {
        alert("Signup successful!");
        // Optionally, clear the form or redirect user
        setState({ username: "", email: "", password: "" });
      }
    } catch (error) {
      console.error("There was an error!", error);
      alert("Signup failed. Please try again.");
    }
  };

  return (
    <div className="form-container sign-up-container">
      <form onSubmit={handleOnSubmit}>
        <h1>Create Account</h1>
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
        <span>or use your email for registration</span>
        <input
          type="text"
          name="username"
          value={state.username}
          onChange={handleChange}
          placeholder="Name"
        />
        <input
          type="email"
          name="email"
          value={state.email}
          onChange={handleChange}
          placeholder="Email"
        />
        <input
          type="password"
          name="password"
          value={state.password}
          onChange={handleChange}
          placeholder="Password"
        />
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
}

export default SignUpForm;
