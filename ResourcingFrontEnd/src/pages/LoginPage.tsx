import { useEffect, useState } from "react";
import { getAllTemps } from "../services/tempServices";

const LoginPage = () => {
  const [_temps, setTemps] = useState([]);

  useEffect(() => {
    const loadTemps = async () => {
      setTemps(await getAllTemps());
    };
    loadTemps();
  }, []);

  const handleSubmit = (event: any) => {
    event.preventDefault();
    console.log("click");
  };

  return (
    <section>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input id="username" type="text" required />

        <label htmlFor="password">Password:</label>
        <input id="password" type="password" required />

        <button type="submit">Log in</button>
      </form>

      {/* Modify or remove the duplicate form as needed */}
      {/* <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input id="username" type="text" required />

        <label htmlFor="password">Password:</label>
        <input id="password" type="password" required />

        <button type="submit">Create new user</button>
      </form> */}
    </section>
  );
};

export default LoginPage;
