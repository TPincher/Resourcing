import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import LoginPage from "./pages/LoginPage";
import JobsPage from "./pages/JobsPage";
import ProfilePage from "./pages/ProfilePage";
import TempsPage from "./pages/TempsPage";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/menu" element={<JobsPage />} />
          <Route path="/game" element={<ProfilePage />} />
          <Route path="/gameHistory" element={<TempsPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
